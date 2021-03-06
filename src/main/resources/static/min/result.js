$(function () {
    function c(a) {
        $.ajax({
            type: "POST",
            url: "/book/v1/search",
            data: "pn=" + a + "&pageSize=5&params=" + encodeURI(d("params")),
            error: function (a) {
                alert("Connection error")
            },
            success: function (a) {
                200 == a.code ? e(a) : alert(a.message)
            }
        })
    }

    function d(a) {
        a = new RegExp("(^|&)" + a + "=([^&]*)(&|$)", "i");
        a = window.location.search.substr(1).match(a);
        return null != a ? unescape(a[2]) : null
    }

    function e(a) {
        $("#bookList").empty();
        0 >= a.data.bookList.length ? $("#bookList").append("<h2>\u672a\u67e5\u8be2\u5230\u7ed3\u679c</h2>") : $.each(a.data.bookList,
            function (a, b) {
                a = $("<h3></h3>").append(b.name);
                var c = $("<p></p>").append(b.description),
                    d = $("<p></p>").append($("<span></span>").append(b.author));
                b = $("<a></a>").attr("href", "/book/detail?bookid=" + b.uid).append("\u67e5\u770b\u8be6\u60c5");
                var e = $("<div></div>").addClass("col-md-3"), f = $("<div></div>").addClass("caption"),
                    g = $("<div></div>").addClass("thumbnail").append();
                g.append(a).append(a).append(c).append(d).append($("<p></p>").append(b));
                f.append(g);
                e.append(f).appendTo("#bookList")
            })
    }

    $(function () {
        setStatus();
        c(1)
    })
});