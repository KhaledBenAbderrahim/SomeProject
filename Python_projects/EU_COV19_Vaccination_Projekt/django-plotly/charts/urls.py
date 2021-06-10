from django.urls import path
from .views import (bar_chart_view,
                    scatter_chart_view,
                    pie_chart_view,
                    bubble_chart_view,example_view,home_page)





urlpatterns = [
    path('bar-chart', bar_chart_view, name='bar-chart'),
    path('scatter-chart', scatter_chart_view, name='scatter-chart'),
    path('pie-chart', pie_chart_view, name='pie-chart'),
    path('bubble-chart', bubble_chart_view, name='bubble-chart'),
    path('my-example', example_view, name='my-example'),
    path('home-page', home_page, name='home-page'),

]

