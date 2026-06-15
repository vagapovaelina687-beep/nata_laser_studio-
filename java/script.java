// Мобильное меню (бургер)
const mobileBtn = document.getElementById('mobileMenuBtn');
const nav = document.querySelector('.nav');

if (mobileBtn) {
    mobileBtn.addEventListener('click', function() {
        nav.classList.toggle('active');
    });
}

// Плавный скролл для всех якорных ссылок
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        const targetId = this.getAttribute('href');
        if (targetId === "#" || targetId === "") return;
        const targetElement = document.querySelector(targetId);
        if (targetElement) {
            e.preventDefault();
            targetElement.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
            if (nav && nav.classList.contains('active')) {
                nav.classList.remove('active');
            }
        }
    });
});

// Обработка кнопок "ПОДРОБНЕЕ"
const detailButtons = document.querySelectorAll('.btn-outline');
detailButtons.forEach(btn => {
    btn.addEventListener('click', (e) => {
        if (btn.getAttribute('href') === '#' || !btn.getAttribute('href') || btn.getAttribute('href') === '') {
            e.preventDefault();
            alert('Страница в разработке. Свяжитесь с нами по контактам!');
        }
    });
});

// Кнопка "Показать еще" для отзывов
const showMoreBtn = document.getElementById('showMoreReviewsBtn');
const reviewsGridElem = document.getElementById('reviewsGrid');

if (showMoreBtn && reviewsGridElem) {
    let isExpanded = false;
    
    showMoreBtn.addEventListener('click', () => {
        isExpanded = !isExpanded;
        
        if (isExpanded) {
            reviewsGridElem.classList.add('show-all');
            showMoreBtn.classList.add('active');
            showMoreBtn.querySelector('span:first-child').textContent = 'Скрыть отзывы';
        } else {
            reviewsGridElem.classList.remove('show-all');
            showMoreBtn.classList.remove('active');
            showMoreBtn.querySelector('span:first-child').textContent = 'Показать еще отзывы';
        }
    });
}

// Анимация появления элементов
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
        }
    });
}, { threshold: 0.1 });

document.querySelectorAll('.service-card, .review-card, .news-item, .about-features li, .hero-content').forEach(el => {
    if (el) {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(el);
    }
});

// Эффект при скролле для шапки (уплотнение)
window.addEventListener('scroll', function() {
    const header = document.querySelector('.header');
    if (header) {
        if (window.scrollY > 50) {
            header.classList.add('scrolled');
        } else {
            header.classList.remove('scrolled');
        }
    }
});

// Слайдер для новостей
const newsGrid = document.getElementById('newsGrid');
const newsPrevBtn = document.getElementById('newsPrevBtn');
const newsNextBtn = document.getElementById('newsNextBtn');

if (newsGrid && newsPrevBtn && newsNextBtn) {
    let newsPosition = 0;
    let newsCardWidth = 0;
    let newsVisibleCards = 0;

    function updateNewsDimensions() {
        if (window.innerWidth <= 768) {
            newsVisibleCards = 1;
        } else if (window.innerWidth <= 992) {
            newsVisibleCards = 2;
        } else {
            newsVisibleCards = 3;
        }
        const firstCard = newsGrid.querySelector('.news-item:not(.placeholder)');
        if (firstCard) {
            newsCardWidth = firstCard.offsetWidth + 30;
        }
        const totalCards = newsGrid.querySelectorAll('.news-item:not(.placeholder)').length;
        const maxPosition = Math.max(0, totalCards - newsVisibleCards);
        if (newsPosition > maxPosition) {
            newsPosition = maxPosition;
        }
        newsGrid.style.transform = `translateX(-${newsPosition * newsCardWidth}px)`;
    }

    newsNextBtn.addEventListener('click', () => {
        const totalCards = newsGrid.querySelectorAll('.news-item:not(.placeholder)').length;
        const maxPosition = Math.max(0, totalCards - newsVisibleCards);
        if (newsPosition < maxPosition) {
            newsPosition++;
            newsGrid.style.transform = `translateX(-${newsPosition * newsCardWidth}px)`;
        }
    });

    newsPrevBtn.addEventListener('click', () => {
        if (newsPosition > 0) {
            newsPosition--;
            newsGrid.style.transform = `translateX(-${newsPosition * newsCardWidth}px)`;
        }
    });

    window.addEventListener('resize', () => {
        updateNewsDimensions();
    });

    updateNewsDimensions();
}

// Слайдер для товаров и услуг
const servicesGrid = document.getElementById('servicesGrid');
const servicesPrevBtn = document.getElementById('servicesPrevBtn');
const servicesNextBtn = document.getElementById('servicesNextBtn');

if (servicesGrid && servicesPrevBtn && servicesNextBtn) {
    let servicesPosition = 0;
    let servicesCardWidth = 0;
    let servicesVisibleCards = 0;

    function updateServicesDimensions() {
        if (window.innerWidth <= 768) {
            servicesVisibleCards = 1;
        } else if (window.innerWidth <= 992) {
            servicesVisibleCards = 2;
        } else {
            servicesVisibleCards = 3;
        }
        const firstCard = servicesGrid.querySelector('.service-card:not(.placeholder)');
        if (firstCard) {
            servicesCardWidth = firstCard.offsetWidth + 30;
        }
        const totalCards = servicesGrid.querySelectorAll('.service-card:not(.placeholder)').length;
        const maxPosition = Math.max(0, totalCards - servicesVisibleCards);
        if (servicesPosition > maxPosition) {
            servicesPosition = maxPosition;
        }
        servicesGrid.style.transform = `translateX(-${servicesPosition * servicesCardWidth}px)`;
    }

    servicesNextBtn.addEventListener('click', () => {
        const totalCards = servicesGrid.querySelectorAll('.service-card:not(.placeholder)').length;
        const maxPosition = Math.max(0, totalCards - servicesVisibleCards);
        if (servicesPosition < maxPosition) {
            servicesPosition++;
            servicesGrid.style.transform = `translateX(-${servicesPosition * servicesCardWidth}px)`;
        }
    });

    servicesPrevBtn.addEventListener('click', () => {
        if (servicesPosition > 0) {
            servicesPosition--;
            servicesGrid.style.transform = `translateX(-${servicesPosition * servicesCardWidth}px)`;
        }
    });

    window.addEventListener('resize', () => {
        updateServicesDimensions();
    });

    updateServicesDimensions();
}