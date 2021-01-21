const bones = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
var summ_throws = 0;
var summ_AI = 0;
var k = 0;

function throw_bones() {
    if (k<3) {
        summ_throws += bones[Math.floor(Math.random()*bones.length)];
        k++;
        document.querySelector('.user-bones').innerHTML += `<div>Бросок № ${k}: ${summ_throws}</div>`
    }
    if (k === 3) {
        document.querySelector('.btn-primary').disabled = true;
        setTimeout(() => {
            ai_throw();
        }, 1);
    }
}

function ai_throw() {
    for (let i = 0; i < 3; i++) {
        summ_AI += bones[Math.floor(Math.random()*bones.length)];
        document.querySelector('.ai-bones').innerHTML += `<div>Бросок № ${i+1}: ${summ_AI}</div>`
    }
    if (summ_throws === summ_AI) {
        document.querySelector('.reload').innerHTML += `<h2>Ничья!</h2>`;
        let headers = document.querySelectorAll('h3');
        headers.forEach(header => {
            header.classList.add('text-secondary');
        })
    }
    if (summ_throws > summ_AI) {
        document.querySelector('.reload').innerHTML += `<h2>Вы выиграли!</h2>`;
        document.querySelector('.player').classList.add('text-success');
        document.querySelector('.ai').classList.add('text-danger');
    }
    if (summ_throws < summ_AI) {
        document.querySelector('.reload').innerHTML += `<h2>Вы проиграли!</h2>`;
        document.querySelector('.player').classList.add('text-danger');
        document.querySelector('.ai').classList.add('text-success');
    }
    document.querySelector('.reload').innerHTML += `<button class="btn btn-success m-3" onclick="window.location.reload()">Сыграть ещё!</button>`
}