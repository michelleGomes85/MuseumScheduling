function togglePasswordVisibility() {
	const passwordField = document.getElementById('password');
	const toggleIcon = document.getElementById('togglePassword');

	if (passwordField.type === 'password') {
		passwordField.type = 'text';
		toggleIcon.classList.remove('fa-eye');
		toggleIcon.classList.add('fa-eye-slash');
	} else {
		passwordField.type = 'password';
		toggleIcon.classList.remove('fa-eye-slash');
		toggleIcon.classList.add('fa-eye');
	}
}

document.addEventListener('DOMContentLoaded', function() {
	M.updateTextFields();
});

document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('select');
	if (elems)
		M.FormSelect.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {

	const datePickers = document.querySelectorAll('.datepicker');
	if (datePickers) {
		M.Datepicker.init(datePickers, {

			format: 'dd/mm/yyyy',
			autoClose: true,
			minDate: new Date(),
			disableDayFn: function(date) {
				return date.getDay() === 1;
			},

			i18n: {
				cancel: 'Cancelar',
				months: [
					'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
					'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'
				],
				monthsShort: [
					'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
					'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'
				],
				weekdays: [
					'Domingo', 'Segunda-feira', 'Terça-feira', 'Quarta-feira',
					'Quinta-feira', 'Sexta-feira', 'Sábado'
				],
				weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
				weekdaysAbbrev: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S']
			}
		});

	}
});
