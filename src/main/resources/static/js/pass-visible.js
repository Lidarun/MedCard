const togglePassword = document.querySelector('#togglePassword');
const togglePassword2 = document.querySelector('#togglePassword2');

const password = document.querySelector('#password');
const password2 = document.querySelector('#password2');

togglePassword.addEventListener('click', function () {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});

togglePassword2.addEventListener('click', function () {
    // toggle the type attribute
    const type2 = password2.getAttribute('type') === 'password' ? 'text' : 'password';
    password2.setAttribute('type', type2);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});