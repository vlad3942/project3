const url = '/getStudents'

let loadingData = null;

const pr = () => {
    return new Promise(r => r())
}
function test() {
    return pr().then(() => {
        return fetch(url)
    }).then(response => response.json())
}
test()
    .then(data => {
        console.log(data);
        let table = document.getElementById('table');
        for (let i = 0; i < data.length; i++) {
            let tr = document.createElement('tr');
            let td = document.createElement('td');
            let td1 = document.createElement('td');
            let td2 = document.createElement('td');
            let td3 = document.createElement('td');
            tr.tabIndex = -1;
            td.innerHTML = data[i].stud_id;
            td1.innerHTML = data[i].stud_name;//заместо id имя
            td2.innerHTML = data[i].enroll_date;
            td3.innerHTML = data[i].group_id.group_id;
            tr.onmouseover = function () {
                td.className = 'hover';
                td1.className = 'hover';
                td2.className = 'hover';
                td3.className = 'hover';
            }
            tr.onmouseout = function () {
                td.className = 'normal';
                td1.className = 'normal';
                td2.className = 'normal';
                td3.className = 'normal';
            }
            tr.onclick = function () {
                console.log(data[i]);
                loadingData = data[i];
            }
            tr.appendChild(td);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            table.appendChild(tr);
        }
    })
    .catch(e => console.error(e));

document.getElementById("edit").addEventListener('click', ev => {
    if (loadingData != null) {
        location.href='/student-edit/' + loadingData.stud_id;
    }
});

document.getElementById("delete").addEventListener('click', ev => {
    if (loadingData != null) {
        location.href='/student-delete/' + loadingData.stud_id;
    }
});
