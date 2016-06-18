$(document).ready(function () {
    $('#management-label-nav').css({color: '#007aff'});
});

$(function () {
    $("#startDate").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'dd/mm/yy'
    });
});

$(function () {
    $("#endDate").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'dd/mm/yy'
    });
});
