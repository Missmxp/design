$(function () {
    null == sessionStorage.getItem("username") ? (alert("\u60a8\u672a\u767b\u5f55"), location.href = "/") : (to_page(1), $("#clearShopcar").click(function () {
        deleteShopcar("0")
    }))
});
function deleteShopcar(a) {
    $.ajax({
        type: "DELETE",
        headers: {AUTH: sessionStorage.getItem("xrf_")},
        url: "/book/v1/shop/car/" + a,
        data: null,
        error: function (a) {
            alert("Connection error")
        },
        success: function (c) {
            200 == c.code ? null == a ? ($("#clearShopcar").attr("disabled", "disabled"), $("tbody").empty()) : location.reload() : alert(c.message)
        }
    })
}
function to_page(a) {
    $.ajax({
        type: "GET",
        headers: {AUTH: sessionStorage.getItem("xrf_")},
        url: "/book/v1/shop/car",
        data: null,
        error: function (a) {
            alert("Connection error")
        },
        success: function (a) {
            200 == a.code ? ($("#clearShopcar").removeAttr("disabled"), build_book_table(a)) : alert(a.message)
        }
    })
}
function build_book_table(a) {
    $("tbody").empty();
    $.each(a.data.bookList, function (a, b) {
        var c = $("<td></td>").append(a + 1), g = $("<td></td>").append(b.name), h = $("<td></td>").append(b.author),
            k = $("<td></td>").append("\uffe5" + b.price), l = $("<td></td>").append(b.nums).attr("id", "nums_" + a),
            e = $("<button>+</button>").addClass("btn btn-info btn-sm increase_btn").attr("nums", "nums_" + a);
        e.attr("add-id", b.uid);
        a = $("<button>-</button>").addClass("btn btn-info btn-sm decrease_btn").attr("nums", "nums_" + a);
        a.attr("decr-id",
            b.uid);
        var d = $("<button>\u7acb\u5373\u8d2d\u4e70</button>").addClass("btn btn-success btn-sm buynow_btn");
        d.attr("buy-id", b.uid).attr("buy-num", b.nums).attr("buy-price", b.price);
        d.attr("data-toggle", "modal").attr("data-target", "#buyModal");
        var f = $("<button>\u5220\u9664</button>").addClass("btn btn-danger btn-sm del_btn");
        f.attr("delete-id", b.uid).attr("bookname", b.name);
        b = $("<td></td>").append(e).append(" ").append(a).append(" ").append(d).append(" ").append(f);
        $("<tr></tr>").append(c).append(g).append(h).append(k).append(l).append(b).appendTo("tbody")
    })
}
$(document).on("click", ".increase_btn", function () {
    var a = $(this).attr("nums");
    20 < parseInt($("#" + a).text()) ? alert("\u6570\u91cf\u8fc7\u591a") : (changeBookNum($(this).attr("add-id"), 1), $("#" + a).text(parseInt($("#" + a).text()) + 1))
});
$(document).on("click", ".decrease_btn", function () {
    var a = $(this).attr("nums");
    1 >= parseInt($("#" + a).text()) || (changeBookNum($(this).attr("decr-id"), 0), $("#" + a).text(parseInt($("#" + a).text()) - 1))
});
$(document).on("click", ".del_btn", function () {
    deleteShopcar($(this).attr("delete-id"))
});
$(document).on("click", ".buynow_btn", function () {
    $("#bookuid_hidden").val($(this).attr("buy-id"));
    $("#book_nums").val($(this).attr("buy-num"));
    $("#bookPrice_buy").text("\u60a8\u9700\u652f\u4ed8\uffe5" + $(this).attr("buy-price") * $(this).attr("buy-num"))
});
$("#buyBtn").click(function () {
    $.ajax({
        type: "POST",
        headers: {AUTH: sessionStorage.getItem("xrf_")},
        url: "/book/v1/buy",
        data: $("#buyModal form").serialize(),
        error: function (a) {
            alert("\u8bf7\u60a8\u5148\u53bb\u767b\u5f55")
        },
        success: function (a) {
            200 == a.code ? (alert("\u8d2d\u4e70\u6210\u529f"), location.reload(!0)) : alert(a.message)
        }
    });
    return !1
});
function changeBookNum(a, c) {
    $.ajax({
        type: "PUT",
        headers: {AUTH: sessionStorage.getItem("xrf_")},
        url: "/book/v1/shop/car",
        data: "bookuid=" + a + "&flag=" + c,
        error: function (a) {
            alert("\u8bf7\u60a8\u5148\u53bb\u767b\u5f55")
        },
        success: function (a) {
            200 != a.code && alert(a.message)
        }
    })
};