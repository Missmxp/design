var interval;
var startTime;
var endTime;
$(function () {

    if (getQueryString("bookid") == null) {
        location.href = "/";
        return;
    }

    getBook(getQueryString("bookid"));

    $.ajax({
        type: "GET",
        url: "/book/v1/panic/detail/" + getQueryString("bookid"),
        data: null,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseJSON.code == 333) {
                alert("三秒防刷");
            } else
                alert("Connection error");
        },
        success: function (result) {
            startTime = new Date(result.data.panic.startTime);
            endTime = new Date(result.data.panic.endTime);
            $("#startTime").html("开始时间：<kbd>" + startTime.toLocaleString() + "</kbd>");
            $("#endTime").html("结束时间：<kbd>" + endTime.toLocaleString() + "</kbd>");
            if(startTime > new Date() || endTime < new Date()) {
                $("#panicBtn").attr("disabled", "disabled");
            }
        }
    });

    interval = window.setInterval(function () {
        ShowCountDown(startTime.getFullYear(), startTime.getMonth() + 1, startTime.getDate(), startTime.getHours(), startTime.getMinutes() + 1, 'countTime');
    }, interval);

    $("#panicBtn").click(function () {
        $.ajax({
            type: "POST",
            headers: {
                AUTH: sessionStorage.getItem("xrf_")
            },
            url: "/book/v1/panic",
            data: "bookUid=" + getQueryString("bookid"),// 你的formid
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.responseJSON.code == 20004) {
                    alert("抢购未开始");
                } else
                    alert("Connection error");
            },
            success: function (data) {
                if (data.code == 200) {
                    $("#panicBtn").attr("disabled", "disabled").text("抢购成功");
                    alert("抢购成功");
                } else
                    alert(data.message);
            }
        });
        return false;
    });
});

function ShowCountDown(year, month, day, min, sec, divname) {
    var now = new Date();
    var endDate = new Date(year, month - 1, day, min, sec);
    var leftTime = endDate.getTime() - now.getTime();
    var leftSecond = parseInt(leftTime / 1000);
    var day1 = Math.floor(leftSecond / (60 * 60 * 24));
    var hour = Math.floor((leftSecond - day1 * 24 * 60 * 60) / 3600);
    var minute = Math.floor((leftSecond - day1 * 24 * 60 * 60 - hour * 3600) / 60);
    var second = Math.floor(leftSecond - day1 * 24 * 60 * 60 - hour * 3600 - minute * 60);
    var cc = document.getElementById(divname);
    cc.innerHTML = "倒计时长：<kbd>" + day1 + "天" + hour + "小时" + minute + "分" + second + "秒</kbd>";
    if (day1 < 0 || hour < 0 || minute < 0 || second <= 0) {
        window.clearInterval(interval);
        if(startTime < new Date() && endTime > new Date()) {
            $("#panicBtn").removeAttr("disabled");
        }
    }

}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function getBook(bookuid) {
    $.ajax({
        type: "GET",
        url: "/book/v1/detail/" + bookuid,
        data: null,
        error: function (request) {
            alert("Connection error");
        },
        success: function (result) {
            var book = result.data.book;
            $("#book_uid").val(book.uid);
            $("#book_name").text(book.name);
            $("#book_author").text(book.author);
            $("#book_price").text(book.price);
            $("#book_desc").text(book.description);
            $("#bookuid_buy").val(book.uid);
            $("#bookuid_add").val(book.uid);
            bookPrice = book.price;
        }
    });
}