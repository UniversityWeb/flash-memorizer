const wrapper = document.querySelector(".wrapper"),
    flip_cards = document.querySelector(".flip_cards"),
    flip_card_frame = document.querySelectorAll(".flip_card_frame"),
    button = document.querySelectorAll(".flip_cards_button"),
    flip_card_index = document.querySelector(".flip_card_index"),
    flip_card_total_element = document.querySelector(".flip_card_total");

process_bar = document.querySelector(".process_bar");

console.log(wrapper, flip_cards, flip_card_frame, button, flip_card_index);


let cardIndex = 0, intervalId,
    flip_card_total = parseInt(flip_card_total_element.textContent);

if (flip_card_total > 0){
    flip_card_index.innerHTML = cardIndex + 1;
    process_bar.style.width = `${((cardIndex+1)/flip_card_total)*100}%`;
}

const autoSlide = () => {
    intervalId = setInterval(() => slideCard(++cardIndex),2000);
}

const slideCard = () => {
    console.log(cardIndex);
    cardIndex = cardIndex === flip_card_total ? 0: cardIndex < 0 ? flip_card_total - 1: cardIndex;
    flip_cards.style.transform = `translate(-${cardIndex*100}%)`
}

const updateClick = (e) => {
    if (flip_card_total > 0){
        clearInterval(intervalId);
        cardIndex += e.target.id === "next" ? 1 : -1;
        slideCard(cardIndex);
        console.log(cardIndex);
        flip_card_index.innerHTML = cardIndex + 1;
        process_bar.style.width = `${((cardIndex+1)/flip_card_total)*100}%`;
    }
}

button.forEach((element) => element.addEventListener("click", updateClick));

wrapper.addEventListener("mouseover", ()=> clearInterval(intervalId))

function toggleFlip(card){
    card.classList.toggle("flipped");
}

function readContent(element) {
    var textToRead = element.querySelector('.flip_card_text').textContent;
    var speechSynthesis = window.speechSynthesis || new SpeechSynthesis();
    var utterance = new SpeechSynthesisUtterance(textToRead);

    utterance.voice = speechSynthesis.getVoices().find(function(voice) {
        return voice.name === 'Google US English';
    });

    speechSynthesis.speak(utterance);
}

function readContentAndStopPropagation(event) {
    event.stopPropagation();
    readContent(event.target.closest('.flip_card_content'));
}

function editDeck(deckId) {
    window.location.href = '/decks/edit/' + deckId;
}