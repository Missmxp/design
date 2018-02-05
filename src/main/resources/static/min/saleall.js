var totalRecord, currentPage;
$(function () {
    null == sessionStorage.getItem("admin") ? location.href = "/admin/login" : to_page(1)
});
function to_page(a) {
    $.ajax({
        type: "POST", url: "/admin/v1/book/saleAll", data: "pn=" + a, error: function (a) {
            alert("Connection error")
        }, success: function (a) {
            build_book_table(a);
            build_page_info(a);
            build_page_nav(a)
        }
    })
}
function build_book_table(a) {
    $("tbody").empty();
    $.each(a.data.page.list, function (a, b) {
        var d = $("<td></td>").append(a + 1), c = $("<td></td>").append(b.bookUid).attr("id", "id_" + a),
            e = $("<td></td>").append("\uffe5" + b.totalPrice), g = $("<td></td>").append(b.nums);
        $("<tr></tr>").append(d).append(c).append(e).append(g).appendTo("tbody");
        getBook("id_" + a, b.bookUid)
    })
}
function build_page_info(a) {
    $("#page_info_area").empty();
    $("#page_info_area").append("\u5f53\u524d<kbd>" + a.data.page.pageNum + "</kbd>\u9875\uff0c\u5171<kbd>" + a.data.page.pages + "</kbd>\u9875\uff0c\u603b\u5171<kbd>" + a.data.page.total + "</kbd>\u6761\u6570\u636e");
    totalRecord = a.data.page.total
}
function build_page_nav(a) {
    $("#page_nav_area").empty();
    var d = $("<ul></ul>").addClass("pagination"),
        b = $("<li></li>").append($("<a></a>").append("\u9996\u9875").attr("href", "#")),
        f = $("<li></li>").append($("<a></a>").append("&laquo;"));
    0 == a.data.page.hasPreviousPage ? (b.addClass("disabled"), f.addClass("disabled")) : (b.click(function () {
        to_page(1)
    }), f.click(function () {
        to_page(a.data.page.pageNum - 1)
    }));
    var c = $("<li></li>").append($("<a></a>").append("&raquo;")),
        e = $("<li></li>").append($("<a></a>").append("\u672b\u9875").attr("href",
            "#"));
    0 == a.data.page.hasNextPage ? (c.addClass("disabled"), e.addClass("disabled")) : (c.click(function () {
        to_page(a.data.page.pageNum + 1)
    }), e.click(function () {
        to_page(a.data.page.pages)
    }));
    d.append(b).append(f);
    $.each(a.data.page.navigatepageNums, function (b, c) {
        b = $("<li></li>").append($("<a></a>").append(c));
        a.data.page.pageNum == c && (currentPage = c, b.addClass("active"));
        b.click(function () {
            to_page(c)
        });
        d.append(b)
    });
    d.append(c).append(e).appendTo($("#page_nav_area"))
}
function getBook(a, d) {
    $.ajax({
        type: "GET",
        headers: {AUTH: sessionStorage.getItem("axrf_")},
        url: "/book/v1/detail/" + d,
        data: null,
        error: function (a) {
            alert("Connection error")
        },
        success: function (b) {
            b = b.data.book;
            $("#" + a).text(b.name)
        }
    })
};