<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agendar Visita</title>
    <script>
        function updateHours(museumId) {
            const hoursSelect = document.getElementById("hour");
            const peopleInput = document.getElementById("people");
            const noMuseumSelected = !museumId;

            // Reseta os campos ao trocar de museu
            hoursSelect.innerHTML = '<option value="" disabled selected>Selecione um horário</option>';
            peopleInput.style.display = "none";
            peopleInput.value = "";
            peopleInput.max = "";
            peopleInput.placeholder = "";

            if (!noMuseumSelected) {
                fetch(`/getAvailableHours?museumId=${museumId}`)
                    .then(response => response.json())
                    .then(data => {
                        hoursSelect.disabled = false;
                        data.forEach(hour => {
                            const option = document.createElement("option");
                            option.value = hour.time;
                            option.textContent = `${hour.time} (Vagas: ${hour.remainingSpots})`;
                            option.dataset.remainingSpots = hour.remainingSpots;
                            hoursSelect.appendChild(option);
                        });
                    });
            } else {
                hoursSelect.disabled = true;
            }
        }

        function updatePeopleInput(selectedHour) {
            const hoursSelect = document.getElementById("hour");
            const peopleInput = document.getElementById("people");

            if (selectedHour) {
                const remainingSpots = hoursSelect.options[hoursSelect.selectedIndex].dataset.remainingSpots;
                peopleInput.style.display = "inline-block";
                peopleInput.max = remainingSpots;
                peopleInput.placeholder = `Máx: ${remainingSpots}`;
                peopleInput.value = "";
            } else {
                peopleInput.style.display = "none";
                peopleInput.value = "";
            }
        }
    </script>
</head>
<body>
    <h1>Agendar Visita</h1>

    <form action="submitSchedule" method="post">
        <!-- Campo para selecionar o museu -->
        <label for="museum">Escolha o museu:</label>
        <select id="museum" name="museumId" onchange="updateHours(this.value)" required>
            <option value="" disabled selected>Selecione um museu</option>
            <c:forEach var="museum" items="${museums}">
                <option value="${museum.id}">${museum.name}</option>
            </c:forEach>
        </select>

        <!-- Campo para selecionar o horário -->
        <div id="hoursSection">
            <label for="hour">Escolha o horário:</label>
            <select id="hour" name="selectedHour" onchange="updatePeopleInput(this.value)" disabled required>
                <option value="" disabled selected>Selecione um horário</option>
            </select>
        </div>

        <!-- Campo para inserir o número de pessoas -->
        <div id="peopleSection">
            <label for="people">Número de pessoas:</label>
            <input 
                type="number" 
                id="people" 
                name="numberOfPeople" 
                min="1" 
                placeholder="Escolha um horário primeiro" 
                style="display:none;" 
                required 
            />
        </div>

        <button type="submit">Confirmar</button>
    </form>
</body>
</html>
