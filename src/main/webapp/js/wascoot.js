var body = $('body');
var html = $('html');

function wasSettings({typography, version, layout, navheaderBg, headerBg, sidebarStyle, sidebarBg, sidebarPosition, headerPosition, containerLayout, direction, primary, secondary}) {
    this.typography = typography || "poppins";
    this.version = version || "light";
    this.layout = layout || "vertical";
    this.navheaderBg = navheaderBg || "color_1";
    this.headerBg = headerBg || "color_1";
    this.sidebarStyle = sidebarStyle || "full";
    this.sidebarBg = sidebarBg || "color_1";
    this.sidebarPosition = sidebarPosition || "static";
    this.headerPosition = headerPosition || "static";
    this.containerLayout = containerLayout || "wide";
    this.direction = direction || "ltr";
    this.primary = primary || "color_1";
    this.secondary = secondary || "color_1";

    this.manageTypography();
    this.manageVersion();
    this.manageLayout();
    this.manageNavHeaderBg();
    this.manageHeaderBg();
    this.manageSidebarStyle();
    this.manageSidebarBg();
    this.manageSidebarPosition();
    this.manageHeaderPosition();
    this.manageContainerLayout();
    this.manageRtlLayout();
    this.manageResponsiveSidebar();
    this.managePrimaryColor();
    this.manageSecondaryColor();
}

wasSettings.prototype.manageTypography = function() {
    body.attr("data-typography", "helveticaneue");

}
wasSettings.prototype.manageLayout = function() {
    body.attr("data-layout", "vertical");

}

wasSettings.prototype.manageSidebarStyle = function() {


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

wasSettings.prototype.manageSidebarPosition = function() {
    body.attr("data-sidebar-position", "static");

}
wasSettings.prototype.manageHeaderPosition = function() {
    body.attr("data-header-position", "static");

}
wasSettings.prototype.manageContainerLayout = function() {
    body.attr("data-container", "wide");

}

wasSettings.prototype.manageResponsiveSidebar = function() {
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

    var handleDataAction = function() {
        $('a[data-action="collapse"]').on("click", function(i) {
            i.preventDefault(),
                $(this).closest(".card").find('[data-action="collapse"] i').toggleClass("mdi-arrow-down mdi-arrow-up"),
                $(this).closest(".card").children(".card-body").collapse("toggle");
        });

        $('a[data-action="expand"]').on("click", function(i) {
            i.preventDefault(),
                $(this).closest(".card").find('[data-action="expand"] i').toggleClass("icon-size-actual icon-size-fullscreen"),
                $(this).closest(".card").toggleClass("card-fullscreen");
        });



        $('[data-action="close"]').on("click", function() {
            $(this).closest(".card").removeClass().slideUp("fast");
        });

        $('[data-action="reload"]').on("click", function() {
            var e = $(this);
            e.parents(".card").addClass("card-load"),
                e.parents(".card").append('<div class="card-loader"><i class=" ti-reload rotate-refresh"></div>'),
                setTimeout(function() {
                    e.parents(".card").children(".card-loader").remove(),
                        e.parents(".card").removeClass("card-load")
                }, 2000)
        });
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

    var handleChatbox = function() {
        jQuery('.bell-link').on('click',function(){
            jQuery('.chatbox').addClass('active');
        });
        jQuery('.chatbox-close').on('click',function(){
            jQuery('.chatbox').removeClass('active');
        });
    }

    var handlePerfectScrollbar = function() {
        if(jQuery('.wasnav-scroll').length > 0)
        {

            const qs = new PerfectScrollbar('.wasnav-scroll');

            qs.isRtl = false;
        }
    }

    var handleBtnNumber = function() {
        $('.btn-number').on('click', function(e) {
            e.preventDefault();

            fieldName = $(this).attr('data-field');
            type = $(this).attr('data-type');
            var input = $("input[name='" + fieldName + "']");
            var currentVal = parseInt(input.val());
            if (!isNaN(currentVal)) {
                if (type == 'minus')
                    input.val(currentVal - 1);
                else if (type == 'plus')
                    input.val(currentVal + 1);
            } else {
                input.val(0);
            }
        });
    }

    var handleDzChatUser = function() {
        jQuery('.dz-chat-user-box .dz-chat-user').on('click',function(){
            jQuery('.dz-chat-user-box').addClass('d-none');
            jQuery('.dz-chat-history-box').removeClass('d-none');
        });

        jQuery('.dz-chat-history-back').on('click',function(){
            jQuery('.dz-chat-user-box').removeClass('d-none');
            jQuery('.dz-chat-history-box').addClass('d-none');
        });

        jQuery('.dz-fullscreen').on('click',function(){
            jQuery('.dz-fullscreen').toggleClass('active');
        });



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

    var handleshowPass = function(){
        jQuery('.show-pass').on('click',function(){
            jQuery(this).toggleClass('active');
            if(jQuery('#dz-password').attr('type') == 'password'){
                jQuery('#dz-password').attr('type','text');
            }else if(jQuery('#dz-password').attr('type') == 'text'){
                jQuery('#dz-password').attr('type','password');
            }
        });
    }


    var handleDzLoadMore = function() {
        $(".dz-load-more").on('click', function(e)
        {
            e.preventDefault();	//STOP default action
            $(this).append(' <i class="fas fa-sync"></i>');

            var dzLoadMoreUrl = $(this).attr('rel');
            var dzLoadMoreId = $(this).attr('id');

            $.ajax({
                method: "POST",
                url: dzLoadMoreUrl,
                dataType: 'html',
                success: function(data) {
                    $( "#"+dzLoadMoreId+"Content").append(data);
                    $('.dz-load-more i').remove();
                }
            })
        });
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

    var handleChartSidebar = function(){
        $('.chat-rightarea-btn').on('click',function(){
            $(this).toggleClass('active');
            $('.chat-right-area').toggleClass('active');
        })
        $('.chat-hamburger').on('click',function(){
            $('.chat-left-area').toggleClass('active');
        })
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
            handleDataAction();
            handleHeaderHight();
            handleMenuTabs();
            handleChatbox();
            //handlePerfectScrollbar();
            handleBtnNumber();
            handleDzChatUser();
            handleDzFullScreen();
            handleshowPass();
            heartBlast();
            handleDzLoadMore();
            handleLightgallery();
            handleCustomFileInput();
            vHeight();
            //handleDatetimepicker();
            //handleCkEditor();
            headerFix();
            //handleChartSidebar();
            //MagnificPopup();
            //handleDraggableCard();
            //handleConverterTheme();
            //handleImageSelect();
            handleSelectPicker();
            handlePageOnScroll();
            //tagify();
            // masonryBox();
        },


        load:function(){
            handlePreloader();
            //handleNiceSelect();
            masonryBox();
        },

        resize:function(){
            vHeight();

        },

        handleMenuPosition:function(){

            handleMenuPosition();
        },
    }

}();

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

    jQuery(window).on('resize',function(){
        /*Check container layout on resize */
        ///alert(dzSettingsOptions.primary);
        dzSettingsOptions.containerLayout = $('#container_layout').val();
        /*Check container layout on resize END */

        new wasSettings(dzSettingsOptions);
    });

})(jQuery);