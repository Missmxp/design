var currentPage;

$(function () {
    setStatus();

    $(function () {
        //去首页
        to_page(1);
    });

    getPanicList();

    getHotBooks();

    function to_page(pn) {
        $.ajax({

                type: "POST",
                url: "/book/v1/list",
                data: "pn=" + pn + "&pageSize=" + 9,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (XMLHttpRequest.responseJSON.code == 333) {
                        alert("操作过快");
//                            location.reload(true);
                    } else
                        alert("Connection error");
                },
                success: function (result) {
                    if (result.code == 200) {
                        //1、解析并显示书籍
                        build_book_table(result);

                        //2、显示分页条信息
                        build_page_nav(result);
                    } else if (result.code == 333) {
                        alert("操作过快");
                    }
                    else {
                        alert(result.message);
                    }
                }
            }
        );
    }

    function build_book_table(result) {
        $("#bookList").empty();
        var books = result.data.page.list;
        $.each(books, function (index, item) {
            var bookName = $("<h3></h3>").append(item.name);
            var bookDesc = $("<p></p>").append(item.description);
            var bookAuthor = $("<p></p>").append($("<span></span>").append(item.author));
            var detail = $("<a></a>").attr("href", "/book/detail?bookid=" + item.uid).append("查看详情");
            var bookPrice = $("<span></span>").addClass("pull-right").css({
                "margin-right": "10px",
                "color": "darkred"
            }).append("￥" + item.price);

            var rootDiv = $("<div></div>").addClass("col-md-4");
            var captionDiv = $("<div></div>").addClass("caption");
            var thumbnailDiv = $("<div></div>").addClass("thumbnail").append();
            thumbnailDiv.append(bookName)
                .append(bookName)
                .append(bookDesc)
                .append(bookAuthor)
                .append($("<p></p>").append(detail).append(bookPrice));
            captionDiv.append(thumbnailDiv);
            rootDiv.append(captionDiv).appendTo("#bookList");
        })
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

    function getPanicList() {
        $.ajax({

                type: "POST",
                url: "/book/v1/panic/list",
                data: null,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (XMLHttpRequest.responseJSON.code == 333) {
                        alert("操作过快");
//                            location.reload(true);
                    } else
                        alert("Connection error");
                },
                success: function (result) {
                    if (result.code == 200) {
//                            alert(result.data.page.list.length);
                        build_panic_list(result);
                    }
                    else {
                        alert(result.message);
                    }
                }
            }
        );
    }

    function build_panic_list(result) {
        var page = result.data.page.list;
        $.each(page, function (index, item) {
            var aDiv = $("<div></div>").addClass("list-group-item");

            var h3Div = $("<h4></h4>").append($("<a>" + "" + "</a>").attr("id", "panic_" + index).attr("href", "/book/panic?bookid=" + item.uid));
            var pDiv = $("<p></p>").text(new Date(item.startTime).toLocaleString() + " 准时开始");

            aDiv.append(h3Div).append(pDiv);
            $("#panic_list").append(aDiv);

            $.ajax({
                type: "GET",
                url: "/book/v1/detail/" + item.uid,
                data: null,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (result) {
                    var book = result.data.book;
                    $("#" + "panic_" + index).text(book.name);
                }
            });
        });
    }

    function getHotBooks() {
        $.ajax({

                type: "GET",
                url: "/book/v1/hotBook",
                data: null,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (XMLHttpRequest.responseJSON.code == 333) {
                        alert("操作过快");
//                            location.reload(true);
                    } else
                        alert("Connection error");
                },
                success: function (result) {
                    if (result.code == 200) {
//                            alert(result.data.page.list.length);
                        build_hotbook_list(result);
                    }
                    else {
                        alert(result.message);
                    }
                }
            }
        );
    }

    function build_hotbook_list(result) {
        var page = result.data.TOP_TEN;
        $.each(page, function (index, item) {
            var aDiv = $("<div></div>").addClass("list-group-item");

            var bDiv = $("<span></span>").addClass("badge").text(index + 1);
            var h3Div = $("<a>" + "" + "</a>").attr("href", "/book/detail?bookid=" + item.uid).text(item.name);

            aDiv.append(bDiv).append(h3Div);
            $("#hotBooks").append(aDiv);

        });
    }
});