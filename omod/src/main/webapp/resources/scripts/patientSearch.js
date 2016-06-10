$(document).ready(function () {
    $('#search-label-nav').css({color: '#007aff'});
});



//Autocomplete for Patient Names
var patientNames = [];

function setPatientNames() {
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

$('#patientName').on('input',function(){
    setPatientNames();
    $('#patientName').typeahead({
        source: patientNames
    });
});


