$(function () {

    $(function () {
        setStatus();
        //去首页
        to_page(1);
    });

    function to_page(pn) {
        $.ajax({
            type: "POST",
            url: "/book/v1/search",
            data: "pn=" + pn + "&pageSize=" + 5 + "&params=" + encodeURI(getQueryString("params")),
            error: function (request) {
                alert("Connection error");
            },
            success: function (result) {
                if (result.code == 200) {
                    //1、解析并显示书籍
                    build_book_table(result);
                } else {
                    alert(result.message);
                }
            }
        });
    }

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function build_book_table(result) {
        $("#bookList").empty();
        if (result.data.bookList.length <= 0) {
            $("#bookList").append("<h2>未查询到结果</h2>");
            return;
        }
        var books = result.data.bookList;
        $.each(books, function (index, item) {
            var bookName = $("<h3></h3>").append(item.name);
            var bookDesc = $("<p></p>").append(item.description);
            var bookAuthor = $("<p></p>").append($("<span></span>").append(item.author));
            var detail = $("<a></a>").attr("href", "/book/detail?bookid=" + item.uid).append("查看详情");

            var rootDiv = $("<div></div>").addClass("col-md-3");
            var captionDiv = $("<div></div>").addClass("caption");
            var thumbnailDiv = $("<div></div>").addClass("thumbnail").append();
            thumbnailDiv.append(bookName)
                .append(bookName)
                .append(bookDesc)
                .append(bookAuthor)
                .append($("<p></p>").append(detail));
            captionDiv.append(thumbnailDiv);
            rootDiv.append(captionDiv).appendTo("#bookList");
        })
    }

});