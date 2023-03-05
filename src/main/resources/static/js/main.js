function copy(text, target) {
    setTimeout(function() {
    $('#copied_tip').remove();
    }, 800);
    $(target).append("<div class='tip' id='copied_tip'>Copied!</div>");
    var input = document.createElement('input');
    input.setAttribute('value', text);
    document.body.appendChild(input);
    input.select();
    var result = document.execCommand('copy');
    document.body.removeChild(input)
    return result;
    }

function highlightNavBar(){
    var link = window.location.pathname;
    var home = document.getElementById("home");
    var gameboard = document.getElementById("gameboard");

    switch (link) {
        case '/home':
            home.classList.add("active");
            break;
        case '/gameboard':
            gameboard.classList.add("active");
            break;
}
    }