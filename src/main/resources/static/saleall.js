var totalRecord;
var currentPage;

$(function () {
    if (sessionStorage.getItem("admin") == null) {
        location.href = "/admin/login";
        return;
    }
    //去首页
    to_page(1);
});

function to_page(pn) {
    $.ajax({
        type: "POST",
        url: "/admin/v1/book/saleAll",
        data: "pn=" + pn,
        error: function (request) {
            alert("Connection error");
        },
        success: function (result) {

            //1、解析并显示书籍
            build_book_table(result);

            //2、显示分页信息
            build_page_info(result);

            //3、显示分页条信息
            build_page_nav(result);

        }
    });
}

function build_book_table(result) {
    $("tbody").empty();
    var books = result.data.page.list;
    $.each(books, function (index, item) {
        var bookCk = $("<td></td>").append(index + 1);//$("<input type='checkbox'>")
        var bookName = $("<td></td>").append(item.bookUid).attr("id", "id_" + index);
        var bookPrice = $("<td></td>").append("￥" + item.totalPrice);
        var bookNums = $("<td></td>").append(item.nums);
        $("<tr></tr>").append(bookCk)
            .append(bookName)
            .append(bookPrice)
            .append(bookNums)
            .appendTo("tbody");
        getBook("id_" + index, item.bookUid);
    })

}

function build_page_info(result) {
    $("#page_info_area").empty();
    //当前<kbd>1</kbd>页，共<kbd>20</kbd>页，总共<kbd>100</kbd>条数据
    $("#page_info_area").append("当前<kbd>" +
        result.data.page.pageNum + "</kbd>页，共<kbd>" +
        result.data.page.pages + "</kbd>页，总共<kbd>" +
        result.data.page.total + "</kbd>条数据");

    totalRecord = result.data.page.total;
}

function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.data.page.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            to_page(1);
        });
        prePageLi.click(function () {
            to_page(result.data.page.pageNum - 1);
        });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.data.page.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.data.page.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.data.page.pages);
        });
    }

    ul.append(firstPageLi).append(prePageLi);
    $.each(result.data.page.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.data.page.pageNum == item) {
            currentPage = item;
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi).appendTo($("#page_nav_area"));
}

function getBook(ele, bookuid) {
    $.ajax({
        type: "GET",
        headers: {
            AUTH: sessionStorage.getItem("axrf_")
        },
        url: "/book/v1/detail/" + bookuid,
        data: null,
        error: function (request) {
            alert("Connection error");
        },
        success: function (result) {
            var book = result.data.book;
            $("#" + ele).text(book.name);
        }
    });
}