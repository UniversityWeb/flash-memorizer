function showFloatingWindow() {
    var floatingWindow = document.getElementById('floatingWindow');
    floatingWindow.style.display = 'block';
}

function closeFloatingWindow() {
    var floatingWindow = document.getElementById('floatingWindow');
    floatingWindow.style.display = 'none';
}

function copyText() {
    var textToCopy = document.getElementById("floatingInput").value;
    var tempInput = document.createElement("input");
    tempInput.value = textToCopy;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand("copy");
    document.body.removeChild(tempInput);

    var toast = new bootstrap.Toast(document.getElementById('successToast'));
    toast.show();
}