document.addEventListener('DOMContentLoaded', function() {
	
	var elems = document.querySelectorAll('.modal');
	M.Modal.init(elems);
		
    $.ajax({
        url: 'getTimesMuseum',
        method: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#timePicker').empty().append('<option value="" disabled selected>Selecione um horário</option>');
            
            data.forEach(function(time) {
                $('#timePicker').append(`<option value="${time}">${time}</option>`);
            });
            
            M.FormSelect.init(document.querySelectorAll('select'));
        },
    });
});

function markPresence(personId, schedulingId) {
    
	var isPresent = $("#presence-" + personId).prop("checked"); 
	
    $.post("updatePresence", { 'personId': personId, 'schedulingId': schedulingId, 'isPresent': isPresent },
        function() {
            $("#presence-" + personId).prop("checked", isPresent);
        });
}
