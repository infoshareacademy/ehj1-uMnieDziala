const URL = "http://localhost:8083/api"

const users = [];
let userId;
let holidays = [];
const select = document.querySelector("#dropdown");
const list = document.querySelector("#holidays-list")

const renderUsers = function (users) {
    select.innerHTML = "";

    users.forEach(user => {
        console.log(user);
        let option = document.createElement("option");
        option.setAttribute("value", user.id);
        option.innerHTML = `${user.firstName} ${user.lastName}`;
        select.appendChild(option)
    })
}

async function fetchUsersAndRender() {
    await fetch(URL + "/employee")
        .then(res => res.json())
        .then(appUsers => {
            users.push(...appUsers)
            userId = users[0].id;
            renderUsers(users);
        })
}

const renderList = function (holidays) {
    list.innerHTML = "";
    let sortedHolidays = holidays.sort((a, b) => a.date > b.date);
    sortedHolidays.forEach(holiday => {
        let li = document.createElement("li");
        li.classList.add("list-group-item", "d-flex", "justify-content-between", "align-items-start");

        let btn = document.createElement("button")
        btn.classList.add("btn", "btn-danger")
        btn.id = `delete${holiday.id}`
        btn.innerHTML = "X"

        let div = document.createElement("div");
        div.classList.add("ms-2", "me-auto")
        div.innerHTML = `${holiday.date}`

        li.appendChild(div)
        li.appendChild(btn)

        btn.addEventListener('click', () => {
            fetch(`${URL}/employee/${userId}/holiday`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    holidaysId: [holiday.id]
                })
            })
                .then(() => fetchHolidaysByUserIdAndRenderList(userId))
        })


        li.addEventListener('mouseover', () => {
            li.classList.add("active")
        })
        li.addEventListener('mouseleave', () => {
            li.classList.remove("active")
        })
        list.appendChild(li);
    })
}

function fetchHolidaysByUserIdAndRenderList(id) {
    fetch(`${URL}/employee/${id}/holiday`)
        .then(res => res.json())
        .then(holidaysApp => {
            holidays = [];
            holidays.push(...holidaysApp)
        })
        .then(() => renderList(holidays))
}


select.addEventListener('change', e => {
    userId = e.target.value;
    fetchHolidaysByUserIdAndRenderList(userId)
        .then(() => renderList(holidays))
})

fetchUsersAndRender()
    .then(() => fetchHolidaysByUserIdAndRenderList(userId))


function onHover() {
    console.log(this)
}