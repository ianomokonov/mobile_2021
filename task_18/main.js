function find_words() {
    let text = document.querySelector('textarea').value;
    text = text.toLowerCase();
    let text_array = text.split(/[.,\/#!$%\^&\*;:{}=\-_`~()/ /«»]/g);
    text_array = text_array.filter(x => x != '');
    let maximum = '';
    let result = [];
    let max = 0;
    text_array.forEach(word => {
        k = text_array.filter(x => x === word).length;
        if (!result.find(x => x.word === word)) {
            result.push({
                word: word,
                count: k
            })
        }
        if (k > max) {
            max = k;
            maximum = `Наибольшее количество повторений (${k}) - "${word}"`;
        }
    })
    let result_string = '';
    result.forEach(res => {
        result_string += `${res.word} - ${res.count} повторений<br>`;
    })
    document.querySelector('.max_count').innerHTML = maximum;
    document.querySelector('.result').innerHTML = `<h4>Результаты анализа:</h4>${result_string}`;
}