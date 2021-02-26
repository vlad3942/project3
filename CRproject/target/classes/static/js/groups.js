const url = '/getGroups'

let loadingData = null;

async function post(url, data) {
    try {
        const request = new Request(url, {
            method: 'POST',
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        const response = await fetch(request)
        return await response;
    } catch (error) {
        console.error(error)
    }
}

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
            tr.tabIndex = -1;
            td.innerHTML = data[i].group_id;
            td1.innerHTML = data[i].faculty;//заместо id имя

            tr.onmouseover = function () {
                td.className = 'hover';
                td1.className = 'hover';
            }
            tr.onmouseout = function () {
                td.className = 'normal';
                td1.className = 'normal';
            }
            tr.onclick = function () {
                console.log(data[i]);
                loadingData = data[i];
            }
            tr.appendChild(td);
            tr.appendChild(td1);
            table.appendChild(tr);
        }
    })
    .catch(e => console.error(e));

document.getElementById("edit").addEventListener('click', ev => {
    if (loadingData != null) {
        //post('/chainword/addLoadingCrossword', loadingCrossword).then(data => console.log(data)).catch(err => console.error(err));
        location.href='/group-edit/' + loadingData.group_id;
    }
});

document.getElementById("delete").addEventListener('click', ev => {
    if (loadingData != null) {
        location.href='/group-delete/' + loadingData.group_id;
    }
});
