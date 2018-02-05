$(function () {

    if (sessionStorage.getItem("username") == null) {
        alert("您未登录");
        location.href = "/";
        return;
    }
    //去首页
    to_page(1);

    $("#clearShopcar").click(function () {
        deleteShopcar("0");
    });

});

function deleteShopcar(bookuid) {
    $.ajax({
        type: "DELETE",
        headers: {
            AUTH: sessionStorage.getItem("xrf_")
        },
        url: "/book/v1/shopcar/" + bookuid,
        data: null,
        error: function (request) {
            alert("Connection error");
        },
        success: function (result) {

            if (result.code == 200) {
                if (bookuid == null) {
                    $("#clearShopcar").attr("disabled", "disabled");
                    $("tbody").empty();
                } else {
                    location.reload();
                }
            } else {
                alert(result.message);
            }
        }
    });
}

function to_page(pn) {
    $.ajax({
        type: "GET",
        headers: {
            AUTH: sessionStorage.getItem("xrf_")
        },
        url: "/book/v1/shopcar",
        data: null,
        error: function (request) {
            alert("Connection error");
        },
        success: function (result) {

            if (result.code == 200) {
                $("#clearShopcar").removeAttr("disabled");
                build_book_table(result);
                //1、解析并显示书籍

                //2、显示分页条信息
//                    build_page_nav(result);
            } else {
                alert(result.message);
            }
        }
    });
}

function build_book_table(result) {
    $("tbody").empty();
    var books = result.data.bookList;
    $.each(books, function (index, item) {
        var bookCk = $("<td></td>").append(index + 1);//$("<input type='checkbox'>")
        var bookName = $("<td></td>").append(item.name);
        var bookAuthor = $("<td></td>").append(item.author);
        var bookPrice = $("<td></td>").append("￥" + item.price);
        var bookStock = $("<td></td>").append(item.nums).attr("id", "nums_" + index);
        var addBtn = $("<button>+</button>").addClass("btn btn-info btn-sm increase_btn").attr("nums", "nums_" + index);
        addBtn.attr("add-id", item.uid);
        var decrBtn = $("<button>-</button>").addClass("btn btn-info btn-sm decrease_btn").attr("nums", "nums_" + index);
        decrBtn.attr("decr-id", item.uid);
        var buyBtn = $("<button>立即购买</button>").addClass("btn btn-success btn-sm buynow_btn");
        buyBtn.attr("buy-id", item.uid).attr("buy-num", item.nums).attr("buy-price", item.price);
        buyBtn.attr("data-toggle", "modal").attr("data-target", "#buyModal");

        var delBtn = $("<button>删除</button>").addClass("btn btn-danger btn-sm del_btn");
        delBtn.attr("delete-id", item.uid).attr("bookname", item.name);
        var bookTd = $("<td></td>").append(addBtn).append(" ").append(decrBtn).append(" ").append(buyBtn).append(" ").append(delBtn);
        $("<tr></tr>").append(bookCk)
            .append(bookName)
            .append(bookAuthor)
            .append(bookPrice)
            .append(bookStock)
            .append(bookTd)
            .appendTo("tbody");
    });
}

$(document).on("click", ".increase_btn", function () {
    var num_id = $(this).attr("nums");
    if(parseInt($("#" + num_id).text()) > 20) {
        alert("数量过多");
        return;
    }
    changeBookNum($(this).attr("add-id"), 1);
    $("#" + num_id).text(parseInt($("#" + num_id).text()) + 1)
});

$(document).on("click", ".decrease_btn", function () {
    var num_id = $(this).attr("nums");
    if(parseInt($("#" + num_id).text()) <= 1) {
        return;
    }
    changeBookNum($(this).attr("decr-id"), 0);
    $("#" + num_id).text(parseInt($("#" + num_id).text()) - 1);

});

$(document).on("click", ".del_btn", function () {
    deleteShopcar($(this).attr("delete-id"));
});

$(document).on("click", ".buynow_btn", function () {
//        $("#buyModal").modal('show');
    $("#bookuid_hidden").val($(this).attr("buy-id"));
    $("#book_nums").val($(this).attr("buy-num"));
    $("#bookPrice_buy").text("您需支付￥" + ($(this).attr("buy-price") * $(this).attr("buy-num")));

});

$('#buyBtn').click(function () {
    $.ajax({
        type: "POST",
        headers: {
            AUTH: sessionStorage.getItem("xrf_")
        },
        url: "/book/v1/buy",
        data: $('#buyModal form').serialize(),// 你的formid
        error: function (request) {
            alert("请您先去登录");
        },
        success: function (data) {
            if (data.code == 200) {
//                    $('#buyModal').modal('hide');
                alert("购买成功");
                location.reload(true);
            } else
                alert(data.message);
        }
    });
    return false;
});

function changeBookNum(bookuid, flag) {
    $.ajax({
        type: "PUT",
        headers: {
            AUTH: sessionStorage.getItem("xrf_")
        },
        url: "/book/v1/shopcar",
        data: "bookuid=" + bookuid + "&flag=" + flag,
        error: function (request) {
            alert("请您先去登录");
        },
        success: function (data) {
            if (data.code == 200) {
            } else
                alert(data.message);
        }
    });
}