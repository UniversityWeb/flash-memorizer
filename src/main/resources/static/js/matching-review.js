//$(document).ready(function() {
//  $(".draggable-card").draggable();
//});

updateElapsedTime();
setInterval(updateElapsedTime, 1);

function updateElapsedTime() {
    const elapsedTimeElement = document.getElementById('elapsed-time');
    const currentTime = new Date().getTime();
    const pageLoadTime = performance.timing.navigationStart;
    const elapsedMilliseconds = currentTime - pageLoadTime;
    elapsedTimeElement.textContent = formatTime(elapsedMilliseconds);
 }

function formatTime(milliseconds) {
    const seconds = Math.floor(milliseconds / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const remainingMilliseconds = milliseconds % 1000;
    const remainingSeconds = seconds % 60;
    const remainingMinutes = minutes % 60;
    return `${padZero(hours)}:${padZero(remainingMinutes)}:${padZero(remainingSeconds)}.${padZero(remainingMilliseconds, 3)}`;
}

function padZero(num, size = 2) {
    return String(num).padStart(size, '0');
}

$(document).ready(function() {
    $(".draggable-card").draggable({
        revert: true, // The card will return to its original position if not dropped on a valid target
        start: function(event, ui) {
            $(this).data("origPosition", ui.helper.position());
        },
        stop: function(event, ui) {
            var currentCard = $(this);
            var origPosition = currentCard.data("origPosition");
            var currentPosition = ui.helper.position();
            var overlappingCard = checkOverlap(currentCard);

            if (overlappingCard) {
                // Compare hiddenPart and displayPart of two cards
                var currentCardHiddenPart = currentCard.data("hidden-part");
                var overlappingCardDisplayPart = overlappingCard.data("display-part");

                if (currentCardHiddenPart === overlappingCardDisplayPart) {
                    // Hide both cards if their parts match
                    currentCard.hide();
                    overlappingCard.hide();
                } else {
                    // Return currentCard to its original position
                    currentCard.animate({
                        top: origPosition.top + "px",
                        left: origPosition.left + "px"
                    });
                }
            }
        }
    });
});

function checkOverlap(currentCard) {
    var overlappingCard = null;
    var currentCardRect = currentCard[0].getBoundingClientRect();
    $(".draggable-card").not(currentCard).each(function() {
        var thisRect = this.getBoundingClientRect();
        if (!(currentCardRect.right < thisRect.left ||
              currentCardRect.left > thisRect.right ||
              currentCardRect.bottom < thisRect.top ||
              currentCardRect.top > thisRect.bottom)) {
            overlappingCard = $(this);
            return false; // Exit the loop if overlapping card found
        }
    });
    return overlappingCard;
}