document.addEventListener("DOMContentLoaded", function () {
/* start set timer */
    var timerInterval;
    var elapsedTime;

    startTimer();

    function startTimer() {
        timerInterval = setInterval(updateElapsedTime, 1);
    }

    function stopTimer() {
        clearInterval(timerInterval);
    }

    function updateElapsedTime() {
        const elapsedTimeElement = document.getElementById('elapsed-time');
        const currentTime = new Date().getTime();
        const pageLoadTime = performance.timing.navigationStart;
        const elapsedMilliseconds = currentTime - pageLoadTime;
        elapsedTimeElement.textContent = formatTime(elapsedMilliseconds);
        elapsedTime = elapsedTimeElement.textContent;
    }

    function updateModalElapsedTime() {
        var elapsedTimeElement = document.getElementById("modal-elapsed-time");
        elapsedTimeElement.textContent = elapsedTime; // Use the elapsed time directly
    }

    function formatTime(milliseconds) {
        const seconds = Math.floor(milliseconds / 1000);
        const minutes = Math.floor(seconds / 60);
        const hours = Math.floor(minutes / 60);
        const remainingSeconds = seconds % 60;
        const remainingMinutes = minutes % 60;
        return `${padZero(hours)}:${padZero(remainingMinutes)}:${padZero(remainingSeconds)}`;
    }

    // Pad a number with zeros to a specified size
    function padZero(num, size = 2) {
        return String(num).padStart(size, '0');
    }

/* end set timer */

/* start set functions of buttons in modal */
    function showModal() {
        const matchingModal = document.getElementById('matchingModal');
        const modal = new bootstrap.Modal(matchingModal);
        modal.show();
    }

    var closeButton = document.getElementById("closeButton");

    closeButton.addEventListener("click", function() {
        var matchingModal = document.getElementById("matchingModal");

        if (matchingModal && matchingModal.classList.contains("show")) {
            matchingModal.classList.remove("show");
            matchingModal.style.display = "none";
            // Remove the "modal-open" class from the body to enable scrolling
            document.body.classList.remove("modal-open");
            // Remove the modal backdrop element
            var modalBackdrop = document.getElementsByClassName("modal-backdrop")[0];
            modalBackdrop.parentNode.removeChild(modalBackdrop);
        }
    });

/* end set functions of buttons in modal */

/* start set interaction for cards */
    const cards = document.querySelectorAll(".card");
    let clickedCards = [];

    cards.forEach(card => {
        card.addEventListener("click", () => {
            card.classList.toggle("clicked");
            card.style.border = "3px solid blue";

            if (card.classList.contains("clicked")) {
                clickedCards.push(card);
            } else {
                card.style.border = "1px solid rgba(0,0,0,.125)";
                const index = clickedCards.indexOf(card);
                if (index !== -1) {
                    clickedCards.splice(index, 1);
                }
            }

            // Check if two cards are clicked
            if (clickedCards.length === 2) {
                const displayPart1 = clickedCards[0].getAttribute("data-displayPart");
                const hiddenPart2 = clickedCards[1].getAttribute("data-hiddenPart");

                // Check if parts match
                if (displayPart1 === hiddenPart2) {
                    // Add an effect to change the border color to green
                    clickedCards.forEach(matchedCard => {
                        matchedCard.style.border = "3px solid green";
                    });

                    // Add a delay before hiding the cards
                    setTimeout(() => {
                        clickedCards.forEach(matchedCard => {
                            matchedCard.style.visibility = "hidden";
                            matchedCard.style.opacity = "0";
                        });

                        // Check if all cards are hidden
                        var allCardsHidden = Array.from(cards).every(card => card.style.visibility === "hidden");
                        if (allCardsHidden) {
                            showModal();
                            stopTimer();
                            updateModalElapsedTime();
                        }

                        clickedCards = []; // Reset clickedCards array
                    }, 100); // Change the delay duration as needed

                } else {
                    // Reset clicked state and the clickedCards array
                    clickedCards.forEach(clickedCard => {
                        clickedCard.classList.remove("clicked");
                        clickedCard.style.border = "1px solid rgba(0,0,0,.125)";
                    });
                    clickedCards = [];
                }
            }
        });
    });

/* end set interaction for cards */
});