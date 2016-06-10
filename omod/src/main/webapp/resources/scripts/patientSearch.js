$(document).ready(function () {
    $('#search-label-nav').css({color: '#007aff'});
});



//Code for slider
$(function() {
    $( "#slider-range" ).slider({
        range: true,
        min: 0,
        max: 100,
        values: [ 0, 100 ],
        slide: function( event, ui ) {
            $( "#amount" ).val( "" + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
        }
    });
    $( "#amount" ).val( "" + $( "#slider-range" ).slider( "values", 0 ) +
        " - " + $( "#slider-range" ).slider( "values", 1 ) );
});


/* Code for Autocomplete feature */

var patientNames = [];
var locations =[];

// Functions to get Data using REST
function getPatientNames() {
    $.ajax({
        url: '/openmrs/ws/rest/v1/patient/?q='+$('#patientName').val(),
        type: 'GET',
        success: function(data){
            var JSONString = JSON.stringify(data);
            var details = JSON.parse(JSONString).results;
            patientNames = [];
            for (var i = 0; i < details.length; i++) {
                patientNames.push(details[i].display.split('-')[1].trim());
            }
        }
    });
}

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

$('#patientName').on('input',function(){
    getPatientNames();
    $('#patientName').typeahead({
        source: patientNames
    });
});

$('#location').on('input',function(){
    getLocations();
    $('#location').typeahead({
        source: locations
    });
});

