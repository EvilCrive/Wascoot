document.addEventListener('DOMContentLoaded', function(event) {
    //getUsersList();
});

function getUsersList() {
    var url = new URL('http://localhost:8080/wascoot-1.0/rest/customer/');
    genericGETRequest(url, getCustomerList);
}

function getCustomerAvgAgeByPC() {
    var url = new URL('http://localhost:8080/wascoot-1.0/rest/customerAvgAgePC/');
    genericGETRequest(url, showCustomerAvgAgeByPC);
}

function getCustomerList(req){
      if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var customers = jsonData['resource-list'];

            var hpcontent = "";
            for(let i=0; i<customers.length; i++){
                  var customer = customers[i]['customer'];
                  hpcontent += "<h1>"+sanitize(customer['cf'])+"</h1>";
                  hpcontent += "<p>name: "+sanitize(customer['name'])+"</p>";
                  hpcontent += "<p>surname: "+sanitize(customer['surname'])+"</p>";
                  hpcontent += "<p>email: "+sanitize(customer['email'])+"</p>";
                  hpcontent += "<p>sex: "+sanitize(customer['sex'])+"</p>";
                  hpcontent += "<p>birthdate: "+sanitize(customer['birthdate'])+"</p>";
                  hpcontent += "<p>postalCode: "+sanitize(customer['postalCode'])+"</p>";
                  hpcontent += "<p>paymentType: "+sanitize(customer['paymentType'])+"</p>";
            }
            document.getElementById("customer-content").innerHTML = hpcontent;
      }
      else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
      }
}

function showCustomerAvgAgeByPC(req){
      if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var data = jsonData['resource-list'];

            var hpcontent = "";
            for(let i=0; i<data.length; i++){
                  var data = data[i]['customer'];
                  hpcontent += "<h1>"+sanitize(customer['cf'])+"</h1>";
                  hpcontent += "<p>name: "+sanitize(customer['name'])+"</p>";
            }
            document.getElementById("customer-content").innerHTML = hpcontent;
      }
      else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
      }
}