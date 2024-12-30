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

document.addEventListener('DOMContentLoaded', function () {
   M.updateTextFields();
});

document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('select');
    M.FormSelect.init(elems);
});
