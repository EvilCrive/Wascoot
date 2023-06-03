var body = $('body');
var html = $('html');

function wascootSettings({typography, layout, sidebarStyle, sidebarPosition, headerPosition, containerLayout}) {
    this.typography = typography || "poppins";
    this.layout = layout||"vertical";
    this.sidebarStyle = sidebarStyle || "full";
    this.sidebarPosition = sidebarPosition || "static";
    this.headerPosition = headerPosition || "static";
    this.containerLayout = containerLayout || "wide";

    this.manageTypography();
    this.manageSidebarStyle();
    this.manageSidebarPosition();
    this.manageHeaderPosition();
    this.manageContainerLayout();
    this.manageResponsiveSidebar();
}

wascootSettings.prototype.manageTypography = function() {
    switch(this.typography) {
        case "cairo":
            body.attr("data-typography", "cairo");
            break;
        case "poppins":
            body.attr("data-typography", "poppins");
            break;
        case "roboto":
            body.attr("data-typography", "roboto");
            break;
        case "opensans":
            body.attr("data-typography", "opensans");
            break;
        case "helveticaneue":
            body.attr("data-typography", "helveticaneue");
            break;
        default:
            body.attr("data-typography", "poppins");
    }
}
wascootSettings.prototype.manageLayout = function() {
    switch(this.layout) {
        case "horizontal":
            this.sidebarStyle === "overlay" ? body.attr("data-sidebar-style", "full") : body.attr("data-sidebar-style", `${this.sidebarStyle}`);
            body.attr("data-layout", "horizontal");
            break;
        case "vertical":
            body.attr("data-layout", "vertical");
            break;
        default:
            body.attr("data-layout", "vertical");
    }
}


wascootSettings.prototype.manageSidebarStyle = function() {


    switch(this.sidebarStyle) {
        case "full":
            body.attr("data-sidebar-style", "full");
            break;
        case "mini":
            body.attr("data-sidebar-style", "mini");
            break;
        case "compact":
            body.attr("data-sidebar-style", "compact");
            break;
        case "modern":
            body.attr("data-sidebar-style", "modern");
            break;
        case "icon-hover":
            body.attr("data-sidebar-style", "icon-hover");

            $("body").on("mouseenter", ".wasnav", function(){
                $('#main-wrapper').addClass('iconhover-toggle');
            }).on("mouseleave", ".wasnav", function(){
                $('#main-wrapper').removeClass('iconhover-toggle');
            });

            break;
        case "overlay":
            this.layout === "horizontal" ? body.attr("data-sidebar-style", "full") : body.attr("data-sidebar-style", "overlay");
            break;
        default:
            body.attr("data-sidebar-style", "full");
    }
}

wascootSettings.prototype.manageSidebarPosition = function() {
    switch(this.sidebarPosition) {
        case "fixed":
            this.sidebarStyle === "overlay" && this.layout === "vertical" || this.sidebarStyle === "modern" ? body.attr("data-sidebar-position", "static") : body.attr("data-sidebar-position", "fixed");
            break;
        case "static":
            body.attr("data-sidebar-position", "static");
            break;
        default:
            body.attr("data-sidebar-position", "static");
    }
}
wascootSettings.prototype.manageHeaderPosition = function() {
    switch(this.headerPosition) {
        case "fixed":
            body.attr("data-header-position", "fixed");
            break;
        case "static":
            body.attr("data-header-position", "static");
            break;
        default:
            body.attr("data-header-position", "static");
    }
}
wascootSettings.prototype.manageContainerLayout = function() {
    switch(this.containerLayout) {
        case "boxed":
            if(this.layout === "vertical" && this.sidebarStyle === "full") {
                body.attr("data-sidebar-style", "overlay");
            }
            body.attr("data-container", "boxed");
            break;
        case "wide":
            body.attr("data-container", "wide");
            break;
        case "wide-boxed":
            body.attr("data-container", "wide-boxed");
            break;
        default:
            body.attr("data-container", "wide");
    }
}

wascootSettings.prototype.manageResponsiveSidebar = function() {
    const innerWidth = $(window).innerWidth();
    if(innerWidth < 1024) {
        body.attr("data-layout", "vertical");
        body.attr("data-container", "wide");
    }

    if(innerWidth > 767 && innerWidth < 1024) {
        body.attr("data-sidebar-style", "mini");
    }

    if(innerWidth < 768) {
        body.attr("data-sidebar-style", "overlay");
    }
}


//////////////
//////////////
/////////////
var W3Crm = function(){
    "use strict"
    /* Search Bar ============ */
    var screenWidth = $( window ).width();
    var screenHeight = $( window ).height();


    var handleSelectPicker = function(){
        if(jQuery('.default-select,.table-responsive select').length > 0 ){
            jQuery('.default-select,.table-responsive select').selectpicker();
        }
    }
    var handlePreloader = function(){
        setTimeout(function() {
            jQuery('#preloader').remove();
            $('#main-wrapper').addClass('show');
        },800);

    }

    var handleMetisMenu = function() {
        if(jQuery('#menu').length > 0 ){
            $("#menu").metisMenu();
        }
        jQuery('.metismenu > .mm-active ').each(function(){
            if(!jQuery(this).children('ul').length > 0)
            {
                jQuery(this).addClass('active-no-child');
            }
        });
    }

    var handleAllChecked = function() {
        $("#checkAll").on('change',function() {
            $("td input, .email-list .custom-checkbox input").prop('checked', $(this).prop("checked"));
        });

        $(".checkAllInput").on('change',function() {
            jQuery(this).parents('.ItemsCheckboxSec').find('input[type="checkbox"]').prop( "checked", $(this).prop("checked") );
        });
    }

    var handleNavigation = function() {
        $(".nav-control").on('click', function() {

            $('#main-wrapper').toggleClass("menu-toggle");

            $(".hamburger").toggleClass("is-active");
        });
    }

    var handleCurrentActive = function() {
        for (var nk = window.location,
                 o = $("ul#menu a").filter(function() {

                     return this.href == nk;

                 })
                     .addClass("mm-active")
                     .parent()
                     .addClass("mm-active");;)
        {

            if (!o.is("li")) break;

            o = o.parent()
                .addClass("mm-show")
                .parent()
                .addClass("mm-active");
        }
    }

    var handleMiniSidebar = function() {
        $("ul#menu>li").on('click', function() {
            const sidebarStyle = $('body').attr('data-sidebar-style');
            if (sidebarStyle === 'mini') {
                console.log($(this).find('ul'))
                $(this).find('ul').stop()
            }
        })
    }

    var handleMinHeight = function() {
        var win_h = window.outerHeight;
        var win_h = window.outerHeight;
        if (win_h > 0 ? win_h : screen.height) {
            $(".content-body").css("min-height", (win_h + 0) + "px");
        };
    }


    var handleHeaderHight = function() {
        const headerHight = $('.header').innerHeight();
        $(window).scroll(function() {
            if ($('body').attr('data-layout') === "horizontal" && $('body').attr('data-header-position') === "static" && $('body').attr('data-sidebar-position') === "fixed")
                $(this.window).scrollTop() >= headerHight ? $('.wasnav').addClass('fixed') : $('.wasnav').removeClass('fixed')
        });
    }

    var handleMenuTabs = function() {
        if(screenWidth <= 991 ){
            jQuery('.menu-tabs .nav-link').on('click',function(){
                if(jQuery(this).hasClass('open'))
                {
                    jQuery(this).removeClass('open');
                    jQuery('.fixed-content-box').removeClass('active');
                    jQuery('.hamburger').show();
                }else{
                    jQuery('.menu-tabs .nav-link').removeClass('open');
                    jQuery(this).addClass('open');
                    jQuery('.fixed-content-box').addClass('active');
                    jQuery('.hamburger').hide();
                }
                //jQuery('.fixed-content-box').toggleClass('active');
            });
            jQuery('.close-fixed-content').on('click',function(){
                jQuery('.fixed-content-box').removeClass('active');
                jQuery('.hamburger').removeClass('is-active');
                jQuery('#main-wrapper').removeClass('menu-toggle');
                jQuery('.hamburger').show();
            });
        }
    }
    /* Header Fixed ============ */
    var headerFix = function(){
        'use strict';
        /* Main navigation fixed on top  when scroll down function custom */
        jQuery(window).on('scroll', function () {

            if(jQuery('.header').length > 0){
                var menu = jQuery('.header');
                $(window).scroll(function(){
                    var sticky = $('.header'),
                        scroll = $(window).scrollTop();

                    if (scroll >= 100){ sticky.addClass('is-fixed');
                    }else {sticky.removeClass('is-fixed');}
                });
            }

        });
        /* Main navigation fixed on top  when scroll down function custom end*/
    }



    var handleDzFullScreen = function() {
        jQuery('.dz-fullscreen').on('click',function(e){
            if(document.fullscreenElement||document.webkitFullscreenElement||document.mozFullScreenElement||document.msFullscreenElement) {
                /* Enter fullscreen */
                if(document.exitFullscreen) {
                    document.exitFullscreen();
                } else if(document.msExitFullscreen) {
                    document.msExitFullscreen(); /* IE/Edge */
                } else if(document.mozCancelFullScreen) {
                    document.mozCancelFullScreen(); /* Firefox */
                } else if(document.webkitExitFullscreen) {
                    document.webkitExitFullscreen(); /* Chrome, Safari & Opera */
                }
            }
            else { /* exit fullscreen */
                if(document.documentElement.requestFullscreen) {
                    document.documentElement.requestFullscreen();
                } else if(document.documentElement.webkitRequestFullscreen) {
                    document.documentElement.webkitRequestFullscreen();
                } else if(document.documentElement.mozRequestFullScreen) {
                    document.documentElement.mozRequestFullScreen();
                } else if(document.documentElement.msRequestFullscreen) {
                    document.documentElement.msRequestFullscreen();
                }
            }
        });
    }


    var handleDatetimepicker = function(){
        if(jQuery("#datetimepicker1").length>0) {
            $('#datetimepicker1').datetimepicker({
                inline: true,
            });
        }
    }

    var handleCkEditor = function(){
        if(jQuery("#ckeditor").length>0) {
            ClassicEditor
                .create( document.querySelector( '#ckeditor' ), {
                    // toolbar: [ 'heading', '|', 'bold', 'italic', 'link' ]
                } )
                .then( editor => {
                    window.editor = editor;
                } )
                .catch( err => {
                    console.error( err.stack );
                } );
        }
    }

    var handleMenuPosition = function(){

        if(screenWidth > 1024){
            $(".metismenu  li").unbind().each(function (e) {
                if ($('ul', this).length > 0) {
                    var elm = $('ul:first', this).css('display','block');
                    var off = elm.offset();
                    var l = off.left;
                    var w = elm.width();
                    var elm = $('ul:first', this).removeAttr('style');
                    var docH = $("body").height();
                    var docW = $("body").width();

                    if(jQuery('html').hasClass('rtl')){
                        var isEntirelyVisible = (l + w <= docW);
                    }else{
                        var isEntirelyVisible = (l > 0)?true:false;
                    }

                    if (!isEntirelyVisible) {
                        $(this).find('ul:first').addClass('left');
                    } else {
                        $(this).find('ul:first').removeClass('left');
                    }
                }
            });
        }
    }

    /* Handle Page On Scroll ============ */
    /* Handle Page On Scroll ============ */
    var handlePageOnScroll = function(event){

        'use strict';
        var headerHeight = parseInt($('.header').css('height'), 10);

        $('.navbar-nav .scroll').on('click', function(event)
        {
            event.preventDefault();

            jQuery('.navbar-nav .scroll').parent().removeClass('active');
            jQuery(this).parent().addClass('active');

            if (this.hash !== "") {
                var hash = this.hash;
                var seactionPosition = parseInt($(hash).offset().top, 10);
                var headerHeight =   parseInt($('.header').css('height'), 10);

                var scrollTopPosition = seactionPosition - headerHeight;
                $('html, body').animate({
                    scrollTop: scrollTopPosition
                }, 800, function(){

                });
            }
        });

        pageOnScroll();
    }

    /* Page On Scroll ============ */
    var pageOnScroll = function(event){

        if(jQuery('.navbar-nav').length > 0){

            var headerHeight = parseInt(jQuery('.header').height(), 10);

            jQuery(document).on("scroll", function(){

                var scrollPos = jQuery(this).scrollTop();
                jQuery('.navbar-nav .scroll').each(function () {
                    var elementLink = jQuery(this);

                    //console.log(this.hash);
                    //console.log(jQuery(this.hash).offset());

                    var refElement = jQuery(elementLink.attr("href"));

                    if(jQuery(this.hash).offset() != undefined){
                        var seactionPosition = parseInt(jQuery(this.hash).offset().top, 10);
                    }else{
                        var seactionPosition = 0;
                    }
                    var scrollTopPosition = (seactionPosition - headerHeight);

                    if (scrollTopPosition <= scrollPos){
                        elementLink.parent().addClass("active");
                        elementLink.parent().siblings().removeClass("active");
                    }
                });

            });
        }
    }



    /* Function ============ */
    return {
        init:function(){
            handleMetisMenu();
            handleAllChecked();
            handleNavigation();
            handleCurrentActive();
            handleMiniSidebar();
            handleMinHeight();
            handleHeaderHight();
            handleMenuTabs();
            handleDzFullScreen();
            handleDatetimepicker();
            handleCkEditor();
            headerFix();
            handleSelectPicker();
            handlePageOnScroll();
        },


        load:function(){
            handlePreloader();
            masonryBox();
        },


        handleMenuPosition:function(){

            handleMenuPosition();
        },
    }

}();
jQuery(document).ready(function (){

    $('#batteryLifeID').flatpickr({

        enableTime: true,

        noCalendar: true,

        dateFormat: 'H:i:S',

        time_24hr: true,

        enableSeconds: true

    });

});


/* Document.ready Start */
jQuery(document).ready(function() {
    $('[data-bs-toggle="popover"]').popover();
    'use strict';
    W3Crm.init();

});
/* Document.ready END */

/* Window Load START */
jQuery(window).on('load',function () {
    'use strict';
    W3Crm.load();
    setTimeout(function(){
        W3Crm.handleMenuPosition();
    }, 1000);

});
/*  Window Load END */
/* Window Resize START */
jQuery(window).on('resize',function () {
    'use strict';
    W3Crm.resize();
    setTimeout(function(){
        W3Crm.handleMenuPosition();
    }, 1000);
});
/*  Window Resize END */

//////
//////
//////

"use strict"

var dzSettingsOptions = {};

function getUrlParams(dParam)
{
    var dPageURL = window.location.search.substring(1),
        dURLVariables = dPageURL.split('&'),
        dParameterName,
        i;

    for (i = 0; i < dURLVariables.length; i++) {
        dParameterName = dURLVariables[i].split('=');

        if (dParameterName[0] === dParam) {
            return dParameterName[1] === undefined ? true : decodeURIComponent(dParameterName[1]);
        }
    }
}

(function($) {

    "use strict"

    dzSettingsOptions = {
        typography: "poppins",
        layout: "vertical",
        sidebarStyle: "full",
        sidebarPosition: "fixed",
        headerPosition: "fixed",
        containerLayout: "full",
    };




    new wascootSettings(dzSettingsOptions);

    jQuery(window).on('resize',function(){
        /*Check container layout on resize */
        dzSettingsOptions.containerLayout = $('#container_layout').val();
        /*Check container layout on resize END */

        new wascootSettings(dzSettingsOptions);
    });

})(jQuery);