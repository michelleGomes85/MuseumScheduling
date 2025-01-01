$(document).ready(function() {
	$('#cpf').mask('000.000.000-00', { reverse: true });
});

$(document).ready(function() {

	$('#museum, #datePicker').on('change blur', function() {

		const museumId = $('#museum').val();
		const date = $('#datePicker').val();

		const formattedDate = moment(date, 'DD/MM/YYYY').format('YYYY-MM-DD');

		if (museumId && formattedDate != "Invalid date") {
			$.ajax({
				url: 'getAvailableTimes',
				type: 'GET',
				data: { museum: museumId, date: formattedDate },
				success:

					function(response) {
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

$('#timePicker').on('change', function() {

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
			success: function(limit) {
				const peopleCount = $('#peopleCount');
				peopleCount.attr('max', limit);
				peopleCount.val('');
				peopleCount.attr('placeholder', `Quantidade de pessoas (máx: ${limit})`);
			},
		});
	}
});

document.addEventListener('DOMContentLoaded', function() {

	const peopleCountInput = document.getElementById('peopleCount');
	const peopleInfoContainer = document.getElementById('peopleInfoContainer');
	const continueButton = document.getElementById('continueButton');
	const submitButton = document.getElementById('submitButton');

	let currentPersonIndex = 0;
	let totalPeople = 0;
	
	peopleCountInput.addEventListener('input', function() {

		const newTotalPeople = parseInt(peopleCountInput.value, 10) || 0;

		peopleInfoContainer.innerHTML = '';
		currentPersonIndex = 0;
		totalPeople = newTotalPeople;

		continueButton.style.display = 'block';
		submitButton.style.display = 'none';

		if (newTotalPeople <= 0) {
			continueButton.style.display = 'none';
		}
	});
	
	$(document).on('input', 'input[id^="people"][id$=".cpf"]', function() {
		$(this).mask('000.000.000-00', { reverse: true });
	});

	function createPersonForm(index) {
		const tickets = ["INTEIRO", "MEIA_ENTRADA", "ISENTO", "MORADOR", "CONDUTOR"];

		const personForm = document.createElement('div');
		personForm.classList.add('person-form-container');

		personForm.classList.add('person-form');

		personForm.innerHTML = `
	        <h6>Pessoa ${index + 1}</h6>

	        <div class="input-field">
	            <i class="material-icons prefix">person</i> 
	            <input id="people[${index}].name" type="text" name="people[${index}].name" required> 
	            <label for="people[${index}].name">Nome</label>
	        </div>

	        <div class="input-field">
	            <i class="material-icons prefix">fingerprint</i> 
	            <input id="people[${index}].cpf" type="text" name="people[${index}].cpf" required> 
	            <label for="people[${index}].cpf">CPF</label>
	        </div>

	        <div class="input-field select">
	            <i class="material-icons prefix">drafts</i> 
	            <select name="people[${index}].ticketType" required>
	                <option value="" disabled selected>Selecione o tipo de ticket</option>
	                <!-- As opções de tickets serão geradas aqui -->
	            </select> 

	            <label for="ticket">Tipo de Ticket</label>
	        </div>
			
	        <div class="input-field">
	            <label>
	                <input type="checkbox" id="acceptTerm${index}" />
	                <span>Aceito os <a href="#!" class="modal-trigger" data-target="termModal">termos de responsabilidade</a></span>
	            </label>
	        </div>
	    `;

		peopleInfoContainer.appendChild(personForm);

		const ticketSelect = personForm.querySelector('select[name="people[' + index + '].ticketType"]');
		tickets.forEach(ticket => {
			const option = document.createElement('option');
			option.value = ticket;
			option.textContent = ticket;
			ticketSelect.appendChild(option);
		});

		M.FormSelect.init(ticketSelect);
	}

	continueButton.addEventListener('click', function() {

		if (currentPersonIndex === 0) {
			totalPeople = parseInt(peopleCountInput.value, 10) || 0;
			if (totalPeople <= 0) {
				return;
			}
		}

		if (currentPersonIndex < totalPeople) {
			createPersonForm(currentPersonIndex);
			currentPersonIndex++;

			if (currentPersonIndex === totalPeople) {
				continueButton.style.display = 'none';
				submitButton.style.display = 'block';
			}
		}
	});
});

document.addEventListener('DOMContentLoaded', function() {
	const elems = document.querySelectorAll('.modal');
	M.Modal.init(elems);
});