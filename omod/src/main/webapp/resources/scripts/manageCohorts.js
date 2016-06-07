
/* MEDIA CAROUSEL */
$(document).ready(function () {
    
    /*MEDIA CAROUSEL*/
    $('#productsCarouselXs').carousel({
        interval: 4000
    })
    $('#eventCarousel .item').each(function () {
        var next = $(this).next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }
        next.children(':first-child').clone().appendTo($(this));

        if (next.next().length > 0) {
            next.next().children(':first-child').clone().appendTo($(this));
        }
        else {
            $(this).siblings(':first').children(':first-child').clone().appendTo($(this));
        }

        if (next.next().next().length > 0) {
            next.next().next().children(':first-child').clone().appendTo($(this));
        }
        else {
            $(this).siblings(':first').children(':first-child').clone().appendTo($(this));
        }
    });
    /*MEDIA CAROUSEL ENDS */

    $(".carousel-inner div:first-child").addClass('active'); //Enables carousel correctly

    $("#cohort-dashboard-link").removeAttr('href');
    
});