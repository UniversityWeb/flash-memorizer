function toggleCurPasswordVisibility() {
    var inputCurPassword = document.getElementById("inputCurPassword");
    if (inputCurPassword.type === "password") {
        inputCurPassword.type = "text";
    } else {
        inputCurPassword.type = "password";
    }
}

function toggleNewPasswordVisibility() {
    var inputNewPassword = document.getElementById("inputNewPassword");
    if (inputNewPassword.type === "password") {
        inputNewPassword.type = "text";
    } else {
        inputNewPassword.type = "password";
    }
}

function toggleReTypeNewPasswordVisibility() {
    var inputReTypeNewPassword = document.getElementById("inputReTypeNewPassword");
    if (inputReTypeNewPassword.type === "password") {
        inputReTypeNewPassword.type = "text";
    } else {
        inputReTypeNewPassword.type = "password";
    }
}