function getCookie(name) {
    // Construct a name=value pair string to search for
    const nameEQ = name + "=";
    // Split document.cookie into individual name=value pairs
    const cookies = document.cookie.split(';');
    // Iterate through all cookies
    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i];
        // Remove any leading whitespace from the cookie string
        while (cookie.charAt(0) == ' ') {
            cookie = cookie.substring(1, cookie.length);
        }
        // Check if the current cookie starts with the nameEQ string
        if (cookie.indexOf(nameEQ) == 0) {
            // Return the cookie value (excluding the name= part)
            return cookie.substring(nameEQ.length, cookie.length);
        }
    }
    // Return null if the cookie is not found
    return null;
}

const userId = getCookie("userId");

if(!userId){
  Swal.fire({
    title: "logged in🤔",
    text: "It seems you are not logged in to the website!",
    icon: "warning",
    showCancelButton: false,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "LOGIN"
}).then((result) => {
  window.location.href = "login.jsp";
});
}
