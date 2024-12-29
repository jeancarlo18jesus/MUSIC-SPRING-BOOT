document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.getElementById('menu-toggle');
    const menu = document.getElementById('menu');
    const mainNav = document.getElementById('main-nav');
    const navPlaceholder = document.getElementById('nav-placeholder');

    menuToggle.addEventListener('click', function() {
        menu.classList.toggle('hidden');
    });

    // Close menu when clicking outside
    document.addEventListener('click', function(event) {
        const isClickInside = menu.contains(event.target) || menuToggle.contains(event.target);
        if (!isClickInside && !menu.classList.contains('hidden')) {
            menu.classList.add('hidden');
        }
    });

    // Adjust menu visibility on window resize
    window.addEventListener('resize', function() {
        if (window.innerWidth >= 640) { // sm breakpoint
            menu.classList.remove('hidden');
        } else {
            menu.classList.add('hidden');
        }
    });

    // Fixed menu on scroll
    let navTop = mainNav.offsetTop;
    function fixNav() {
        if (window.scrollY >= navTop) {
            mainNav.classList.add('fixed', 'top-0', 'left-0', 'right-0', 'z-50');
            mainNav.style.maxWidth = '1200px';
            mainNav.style.margin = '0 auto';
            navPlaceholder.style.height = mainNav.offsetHeight + 'px';
            navPlaceholder.classList.remove('hidden');
        } else {
            mainNav.classList.remove('fixed', 'top-0', 'left-0', 'right-0', 'z-50');
            mainNav.style.maxWidth = '';
            mainNav.style.margin = '';
            navPlaceholder.classList.add('hidden');
        }
    }

    window.addEventListener('scroll', fixNav);
});


// MUSICA MODAL DE INSERTAR , ACTUALIZAR Y ELIMINAR
 
const modal = document.getElementById('alertModal');
// Ocultar el modal después de 1 segundo
setTimeout(() => {
    modal.classList.remove('translate-y-0');
    modal.classList.add('-translate-y-full');
}, 3000);

const toastNotification = document.getElementById('toastNotification');
setTimeout(() => {
  toastNotification.classList.remove('translate-y-0', 'opacity-100');
  toastNotification.classList.add('translate-y-[-120%]', 'opacity-0');
}, 3000);
/// HEADER DE NAVBAR ACTUALIZADO 2.0
const menuToggle = document.getElementById('menu-toggle');
      const closeMenu = document.getElementById('close-menu');
      const dropdownMenu = document.getElementById('dropdown-menu');
      const menuItems = dropdownMenu.querySelectorAll('li');

      function showMenu() {
        dropdownMenu.classList.remove('-translate-y-full');
        dropdownMenu.classList.add('translate-y-0');
        menuItems.forEach((item, index) => {
          setTimeout(() => {
            item.classList.remove('-translate-y-full');
            item.classList.add('translate-y-0');
          }, index * 100);
        });
      }

      function hideMenu() {
        menuItems.forEach((item, index) => {
          setTimeout(() => {
            item.classList.remove('translate-y-0');
            item.classList.add('-translate-y-full');
          }, index * 100);
        });
        setTimeout(() => {
          dropdownMenu.classList.remove('translate-y-0');
          dropdownMenu.classList.add('-translate-y-full');
        }, menuItems.length * 100);
      }

      menuToggle.addEventListener('click', showMenu);
      closeMenu.addEventListener('click', hideMenu);