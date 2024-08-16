document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('flawForm');

    if (form) {
        // Set the userId dynamically from localStorage
        const userId = localStorage.getItem('userId');
        if (userId) {
            const userIdField = document.getElementById('userId');
            if (userIdField) {
                userIdField.value = userId;
            } else {
                console.error('User ID input field is not found.');
            }
        } else {
            console.error('User ID is not found in localStorage.');
        }

        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const application = document.getElementById('application').value;
            const module = document.getElementById('module').value;
            const description = document.getElementById('description').value;
            const userId = document.getElementById('userId').value;

            if (!userId) {
                console.error('User ID is required');
                return;
            }

            const flaw = {
                application: application,
                module: module,
                description: description,
                assignedTo: 'Assigning to engineer',
                status: 'Open',
                userId: userId
            };

            fetch('http://localhost:8088/flawtrack/api/flaws', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({flaw:flaw,userId:userId})
            })
            .then(response => response.text())  // Read response as text first
            .then(text => {
                try {
                    const data = JSON.parse(text);  // Attempt to parse text as JSON

                    if (data.error) {
                        console.error('Error:', data.error);
                    } else if (data.message) {
                        // Redirect on successful creation
                        window.location.href = '/flawtrack/thank.html';
                       form.reset();
                    } else {
                        console.error('Unexpected response format:', text);
                    }
                } catch (e) {
                    console.error('Response is not valid JSON:', text);
                }
            })
            .catch(error => console.error('Error:', error));
        });
    }

    const flawsTable = document.getElementById('flawsTable');

    if (flawsTable) {
        fetch('http://localhost:8088/flawtrack/api/flaws')
            .then(response => response.text())  // Read response as text first
            .then(text => {
                try {
                    const flaws = JSON.parse(text);  // Attempt to parse text as JSON

                    if (!Array.isArray(flaws)) {
                        throw new TypeError('Expected an array of flaws');
                    }

                    const tbody = flawsTable.querySelector('tbody');
                    if (tbody) {
                        flaws.forEach(flaw => {
                            const row = document.createElement('tr');
                            row.innerHTML = `
                                <td>${flaw.id}</td>
                                <td>${flaw.application}</td>
                                <td>${flaw.module}</td>
                                <td>${flaw.description}</td>
                                <td>${flaw.assignedTo}</td>
                                <td>${flaw.status}</td>
                            `;
                            tbody.appendChild(row);
                        });
                    } else {
                        console.error('Table body (tbody) element is not found.');
                    }
                } catch (e) {
                    console.error('Response is not valid JSON:', text);
                }
            })
            .catch(error => console.error('Error:', error));
    }
});
