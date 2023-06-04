
document.addEventListener("DOMContentLoaded", function ()
{
    var ADMINS_LIST;
    async function adminLog() {
        const response = await fetch('administrators.json');
        if (response.ok) {
            const data = await response.json();
            console.log(data);
            let items = data;
            console.log("items", items);
            items = items.splice(0, 10);
            ADMINS_LIST = items.map((it) => it);
            generate_entire_list_of_admins(ADMINS_LIST);
            var select_admin = document.getElementById("admin_select");
            ADMINS_LIST.map((adm) => {
                var opt = document.createElement("option");
                opt.value = adm.title;
                opt.text = adm.title;
                select_admin.appendChild(opt);
            });
        } else {
            console.log(response);
        }
    }
    adminLog();

    function generate_entire_list_of_admins(lista) {
        var row = "";
        var admin_container = document.getElementById("admin_container");
        for (let i = 0; i < lista.length; i++) {
            if (lista[i]["title"]) {
                if (i % 4 === 0 || i === 0) {
                    row = document.createElement("div");
                    row.classList.add("row");
                }
                var col = createAdminElement(lista[i]);
                row.appendChild(col);
                if ((i + 1) % 4 === 0 || i === lista.length - 1) {
                    admin_container.appendChild(row);
                }
            }
        }
    }
    function createAdminElement(item) {
        const col = document.createElement("div");
        col.classList.add("col-md-3");
        const div = document.createElement("div");
        div.classList.add("adm");
        div.id = item["id"];
        const image = document.createElement("img");
        image.src = "./images/" + item["id"].toString() + ".png";
        image.id = "img_" + item["id"];
        image.className = "image-lst";
        // this is to open the modal
        image.setAttribute("data-bs-toggle", "modal");
        image.setAttribute("data-bs-target", "#infoModal");

        //on selecting the administrator this will fill the info box with the data to be presented
        image.addEventListener("click", function (event) {
            fillModalData(event, item["title"], item["num_adms"], item["admin_id"],item['email']);
        });

        // Create an h6 element and set its text content
        const title = document.createElement("h6");
        title.textContent = item.title;

        //Create a button element and set its text content
        var button = document.createElement("button");
        button.classList = ["btn btn-danger btn-sm"];
        div.appendChild(image);
        div.appendChild(title);
        col.append(div);
        return col;
    }

// this fills the info box with all the administrator data
    function fillModalData(event, title, adms, admin_id,email) {
        event.preventDefault();
        var title_data = document.getElementById("admin_title");
        title_data.textContent = title;
        var adms_data = document.getElementById("adms");
        adms_data.textContent = adms;
        var id_data = document.getElementById("admin_id");
        id_data.textContent = admin_id;
        var email_data = document.getElementById("email");
        email_data.textContent = email;
    }
});
