document.addEventListener('DOMContentLoaded', function() {
		
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
	var $peoplePresentCell = $("#people-present-" + schedulingId);

	$.post("updatePresence", { 'personId': personId, 'schedulingId': schedulingId, 'isPresent': isPresent },
		function() {
			
			if ($peoplePresentCell.length != 0) {
				var currentPeoplePresent = parseInt($peoplePresentCell.text(), 10);
				
				if (isPresent)
					$peoplePresentCell.text(currentPeoplePresent + 1);
				else
					$peoplePresentCell.text(currentPeoplePresent - 1);
			} 

			$("#presence-" + personId).prop("checked", isPresent);
		});
}
