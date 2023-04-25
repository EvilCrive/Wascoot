document.addEventListener('DOMContentLoaded', function(event) {
    getCustomerList();
});

function getUsersList() {
    var url = new URL('http://localhost:8080/wascoot-1.0/rest/customer/');
    genericGETRequest(url, getCustomerList);
}

function getCustomerList(req){
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status == 200) {
             var jsonData = JSON.parse(req.responseText);
             var customer = jsonData['customer'];

            var hpcontent = "";
            for(let i=0; i<customer.length; i++){
                hpcontent += "<h1>"+sanitize(customer[i]['cf'])+"</h1>";
                hpcontent += "<p>name: "+sanitize(customer[i]['email'])+"</p>";
                hpcontent += "<p>surname: "+sanitize(customer[i]['surname'])+"</p>";
                hpcontent += "<p>email: "+sanitize(customer[i]['email'])+"</p>";
                hpcontent += "<p>sex: "+sanitize(customer[i]['sex'])+"</p>";
                hpcontent += "<p>birthdate: "+sanitize(customer[i]['birthdate'])+"</p>";
                hpcontent += "<p>postalCode: "+sanitize(customer[i]['postalCode'])+"</p>";
                hpcontent += "<p>paymentType: "+sanitize(customer[i]['paymentType'])+"</p>";
            }
            document.getElementById("customer-content").innerHTML = hpcontent;
        }
        else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
        }
    }
}