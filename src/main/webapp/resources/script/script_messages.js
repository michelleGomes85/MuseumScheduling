function showModal() {

	const modal_error = document.getElementById("modalMessage-error");
	const modal_sucess = document.getElementById("modalMessage-sucess");

	if (modal_error)
		modal_error.style.display = "flex";

	if (modal_sucess)
		modal_sucess.style.display = "flex";
}

function closeModal() {
	const modal_error = document.getElementById("modalMessage-error");
	const modal_sucess = document.getElementById("modalMessage-sucess");

	if (modal_error)
		modal_error.style.display = "none";

	if (modal_sucess)
		modal_sucess.style.display = "none";
}

document.addEventListener("DOMContentLoaded", () => {
	showModal();
});