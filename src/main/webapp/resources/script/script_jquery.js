$(document).ready(function() {
	$('#cpf').mask('000.000.000-00', { reverse: true });
});

$(document).ready(function () {
	
    // Monitorar mudanças nos campos 'museum' e 'datePicker'
    $('#museum, #datePicker').on('change blur', function () {
	
        const museumId = $('#museum').val();
        const date = $('#datePicker').val();
		
		const formattedDate = moment(date, 'DD/MM/YYYY').format('YYYY-MM-DD');
		
        if (museumId && formattedDate != "Invalid date") {
            $.ajax({
                url: 'getAvailableTimes', 
                type: 'GET',
                data: { museum: museumId, date: formattedDate },
                success: 
				
				function (response) {
					$('.hide-field').show();
					
                    const timePicker = $('#timePicker');
					
                    timePicker.empty();
                    timePicker.append('<option value="" disabled selected>Escolha um horário</option>');
                    response.forEach(time => {
                        timePicker.append(`<option value="${time}">${time}</option>`);
                    });
					
					M.FormSelect.init(timePicker);
                },
            });
        }
    });
});

$('#peopleCount').prop('disabled', true);

$('#timePicker').on('change', function () {
    
	const museumId = $('#museum').val();
    const date = $('#datePicker').val();
	
    const selectedTime = $(this).val();
	
	const formattedDate = moment(date, 'DD/MM/YYYY').format('YYYY-MM-DD');

    if (museumId && formattedDate && selectedTime) {
		
		$('#peopleCount').prop('disabled', false);

        $.ajax({
            url: 'getLimitForTime',
            type: 'GET',
            data: { museum: museumId, date: formattedDate, time: selectedTime },
            success: function (limit) {
                const peopleCount = $('#peopleCount');
                peopleCount.attr('max', limit);
                peopleCount.val('');
                peopleCount.attr('placeholder', `Quantidade de pessoas (máx: ${limit})`);
            },
        });
    }
});
