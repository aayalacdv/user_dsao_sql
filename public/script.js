$(document).ready(function(){

    /* Sumar o restar cantidad productos tienda */
    $('.input-group').find('.quantity-plus').click(function(e) {
        e.preventDefault();

        var quantity = parseInt($(this).parent().find('.input-number').val());


        $(this).parent().find('.input-number').val(quantity + 1);
    });

    $('.input-group').find('.quantity-minus').click(function(e) {
        e.preventDefault();

        var quantity = parseInt($(this).parent().find('.input-number').val());

        if(quantity > 1) {
            $(this).parent().find('.input-number').val(quantity - 1);
        }
    });

    /* Volver al principio de la pagina */
    var back2Top = document.getElementById("backtotop");

    window.onscroll = function() {
        scrollFunction();
    }

    function scrollFunction() {
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        back2Top.style.display = "block";
      } else {
        back2Top.style.display = "none";
      }
    }

    $('.backtotop').on("click", function() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    });
 });
