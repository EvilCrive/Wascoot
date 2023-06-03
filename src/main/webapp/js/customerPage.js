const paymentMethods_changeColor = () => {
    const pM_items = document.getElementsByClassName('paymentMethod');

    for (const element of pM_items) {
        let color_toAdd;
        switch (element.innerHTML) {
            case 'Credit Card' :
                color_toAdd = color_CREDIT;
                break;
            case 'Paypal' :
                color_toAdd = color_PAYPAL;
                break;
            case 'VISA Debit' :
                color_toAdd = color_VISA;
                break;
            default :
                color_toAdd = 'gray';
                break;
        }
        element.style.backgroundColor = color_toAdd;
        element.style.color = 'black';
    }
}
const postalCode_nullToSlash = () => {
    const pC_items = document.getElementsByClassName('postalCode');

    for (const element of pC_items) {
        if (element.innerHTML === '') {
            element.innerHTML = '-';
        }
    }
}

const color_PAYPAL = '#7ab2fa';
const color_VISA = '#fa7a7a';
const color_CREDIT = '#bee0ab';
paymentMethods_changeColor()
postalCode_nullToSlash()