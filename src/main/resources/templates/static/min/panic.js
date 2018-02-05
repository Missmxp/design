var interval, startTime, endTime;
$(function () {
    null == getQueryString("bookid") ? location.href = "/" : (getBook(getQueryString("bookid")), $.ajax({
        type: "GET", url: "/book/v1/panic/detail/" + getQueryString("bookid"), data: null, error: function (a, c, d) {
            333 == a.responseJSON.code ? alert("\u4e09\u79d2\u9632\u5237") : alert("Connection error")
        }, success: function (a) {
            startTime = new Date(a.data.panic.startTime);
            endTime = new Date(a.data.panic.endTime);
            $("#startTime").html("\u5f00\u59cb\u65f6\u95f4\uff1a<kbd>" + startTime.toLocaleString() + "</kbd>");
            $("#endTime").html("\u7ed3\u675f\u65f6\u95f4\uff1a<kbd>" +
                endTime.toLocaleString() + "</kbd>");
            (startTime > new Date || endTime < new Date) && $("#panicBtn").attr("disabled", "disabled")
        }
    }), interval = window.setInterval(function () {
        ShowCountDown(startTime.getFullYear(), startTime.getMonth() + 1, startTime.getDate(), startTime.getHours(), startTime.getMinutes() + 1, "countTime")
    }, interval), $("#panicBtn").click(function () {
        $.ajax({
            type: "POST",
            headers: {AUTH: sessionStorage.getItem("xrf_")},
            url: "/book/v1/panic",
            data: "bookUid=" + getQueryString("bookid"),
            error: function (a, c, d) {
                20004 == a.responseJSON.code ?
                    alert("\u62a2\u8d2d\u672a\u5f00\u59cb") : alert("Connection error")
            },
            success: function (a) {
                200 == a.code ? ($("#panicBtn").attr("disabled", "disabled").text("\u62a2\u8d2d\u6210\u529f"), alert("\u62a2\u8d2d\u6210\u529f")) : alert(a.message)
            }
        });
        return !1
    }))
});
function ShowCountDown(a, c, d, b, e, f) {
    var g = new Date;
    a = (new Date(a, c - 1, d, b, e)).getTime() - g.getTime();
    b = parseInt(a / 1E3);
    a = Math.floor(b / 86400);
    c = Math.floor((b - 86400 * a) / 3600);
    d = Math.floor((b - 86400 * a - 3600 * c) / 60);
    b = Math.floor(b - 86400 * a - 3600 * c - 60 * d);
    document.getElementById(f).innerHTML = "\u5012\u8ba1\u65f6\u957f\uff1a<kbd>" + a + "\u5929" + c + "\u5c0f\u65f6" + d + "\u5206" + b + "\u79d2</kbd>";
    if (0 > a || 0 > c || 0 > d || 0 >= b) window.clearInterval(interval), startTime < new Date && endTime > new Date && $("#panicBtn").removeAttr("disabled")
}
function getQueryString(a) {
    a = new RegExp("(^|&)" + a + "=([^&]*)(&|$)", "i");
    a = window.location.search.substr(1).match(a);
    return null != a ? unescape(a[2]) : null
}
function getBook(a) {
    $.ajax({
        type: "GET", url: "/book/v1/detail/" + a, data: null, error: function (a) {
            alert("Connection error")
        }, success: function (a) {
            a = a.data.book;
            $("#book_uid").val(a.uid);
            $("#book_name").text(a.name);
            $("#book_author").text(a.author);
            $("#book_price").text(a.price);
            $("#book_desc").text(a.description);
            $("#bookuid_buy").val(a.uid);
            $("#bookuid_add").val(a.uid);
            bookPrice = a.price
        }
    })
};