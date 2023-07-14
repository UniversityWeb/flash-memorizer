const board = document.getElementById('board');
const cardData = [
    { content: 'Card 1' },
    { content: 'Card 2' },
    { content: 'Card 3' },
    { content: 'Card 4' },
    { content: 'Card 5' },
    { content: 'Card 6' },
    { content: 'Card 7' },
    { content: 'Card 8' },
    { content: 'Card 9' },
    { content: 'Card 10' }
];
cardData.forEach((data) => {
    const card = createCard(data.content);
    board.appendChild(card);
});


positionCardsRandomly();
window.addEventListener('resize', positionCardsRandomly);

board.addEventListener('mousedown', startDrag);
board.addEventListener('mousemove', drag);
board.addEventListener('mouseup', stopDrag);
board.addEventListener('touchstart', startDrag);
board.addEventListener('touchmove', drag);
board.addEventListener('touchend', stopDrag);

updateElapsedTime();
setInterval(updateElapsedTime, 1);


function createCard(content) {
    const card = document.createElement('div');
    card.classList.add('card');
    card.draggable = true;
    card.textContent = content;
    return card;
}

function positionCardsRandomly() {
    const cards = board.querySelectorAll('.card');
    const boardSize = board.offsetWidth;
    cards.forEach(card => {
        const cardSize = card.offsetWidth;
        const { left, top } = getRandomPosition(boardSize, cardSize);
        card.style.left = `${left}px`;
        card.style.top = `${top}px`;
  });
}

function getRandomPosition(boardSize, cardSize) {
    const minLeft = cardSize / 2;
    const maxLeft = boardSize - cardSize / 2;
    const minTop = cardSize / 2;
    const maxTop = cardSize * 3;
    const randomLeft = Math.floor(Math.random() * (maxLeft - minLeft + 1)) + minLeft;
    const randomTop = Math.floor(Math.random() * (maxTop - minTop + 1)) + minTop;
    return { left: randomLeft, top: randomTop };
}

let isDragging = false;
let offset = [0, 0];
let activeCard = null;

function startDrag(e) {
    if (e.type === 'touchstart') {
        offset = [
            e.target.offsetLeft - e.touches[0].clientX,
            e.target.offsetTop - e.touches[0].clientY
        ];
    } else {
        offset = [
            e.target.offsetLeft - e.clientX,
            e.target.offsetTop - e.clientY
        ];
    }
    isDragging = true;
    activeCard = e.target;
}

function drag(e) {
    if (!isDragging) return;
    e.preventDefault();
    if (e.type === 'touchmove') {
        activeCard.style.left = e.touches[0].clientX + offset[0] + 'px';
        activeCard.style.top = e.touches[0].clientY + offset[1] + 'px';
    } else {
        activeCard.style.left = e.clientX + offset[0] + 'px';
        activeCard.style.top = e.clientY + offset[1] + 'px';
    }
}

function stopDrag() {
    isDragging = false;
    activeCard = null;
}

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
