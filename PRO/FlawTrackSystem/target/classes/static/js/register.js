document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the form from submitting

        // Validate inputs
        let isValid = true;

        // Validate name (required)
        const nameInput = document.getElementById('name');
        if (!nameInput.value.trim()) {
            isValid = false;
            setErrorFor(nameInput, 'Name cannot be blank');
        } else {
            setSuccessFor(nameInput);
        }

        // Validate email (required and format)
        const emailInput = document.getElementById('email');
        if (!emailInput.value.trim()) {
            isValid = false;
            setErrorFor(emailInput, 'Email cannot be blank');
        } else if (!isEmailValid(emailInput.value.trim())) {
            isValid = false;
            setErrorFor(emailInput, 'Email is not valid');
        } else {
            setSuccessFor(emailInput);
        }

        // Validate password (required)
        const passwordInput = document.getElementById('password');
        if (!passwordInput.value.trim()) {
            isValid = false;
            setErrorFor(passwordInput, 'Password cannot be blank');
        } else {
            setSuccessFor(passwordInput);
        }

        // Validate phone number (required and numeric)
        const phoneInput = document.getElementById('phoneno');
        if (!phoneInput.value.trim()) {
            isValid = false;
            setErrorFor(phoneInput, 'Phone number cannot be blank');
        } else if (!isPhoneValid(phoneInput.value.trim())) {
            isValid = false;
            setErrorFor(phoneInput, 'Phone number is not valid');
        } else {
            setSuccessFor(phoneInput);
        }

        // If all inputs are valid, submit the form
        if (isValid) {
            form.submit();
        }
    });

    function setErrorFor(input, message) {
        const formControl = input.parentElement; // .form-control
        const small = formControl.querySelector('small');

        // Add error message and class
        small.innerText = message;
        formControl.className = 'form-control error';
    }

    function setSuccessFor(input) {
        const formControl = input.parentElement; // .form-control
        formControl.className = 'form-control success';
    }

    function isEmailValid(email) {
        // Basic email validation using regex
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    function isPhoneValid(phone) {
        // Basic phone number validation
        return /^\d{10}$/.test(phone); // Assuming phone number is 10 digits
    }
});
