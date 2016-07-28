$(document).ready(function () {
    $('#search-label-nav').css({color: '#007aff'});
});


$(function () {
    $("#startDate").datepicker({
        changeMonth: true,
        changeYear: true
    });
});


/* Code for Autocomplete feature */

var locations =[];

// Functions to get Data using REST

function getLocations() {
    $.ajax({
        url: '/openmrs/ws/rest/v1/location?q='+$('#location').val(),
        type: 'GET',
        success: function(data){
            var JSONString = JSON.stringify(data);
            var details = JSON.parse(JSONString).results;
            locations = [];
            for (var i = 0; i < details.length; i++) {
                locations.push(details[i].display);
            }
        }
    });
}

// Autocomplete driver methods

$('#location').on('input',function(){
    getLocations();
    $('#location').typeahead({
        source: locations
    });
});

