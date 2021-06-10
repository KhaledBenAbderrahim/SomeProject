from django.shortcuts import render
import random
from .views_helper import vaccin_country_df,vaccin_count,vaccin_eu_count

def bar_chart_view(request):
    """
    Bar chart
    :param request:
    :return:
    """
    dfv = vaccin_country_df()
    print(dfv)
    print(dfv['Vaccin'].values.tolist())
    print(dfv['total_country'].values.tolist())

    # Generate a list from 'vaccin_country_df'
    x = dfv['Vaccin'].values.tolist()
    y = dfv['total_country'].values.tolist()

    # Bar chart colors
    colors = ['limegreen', ] * 5

    # Create a trace json to hold graph data
    trace = {
        'x': x,
        'y': y,
        'type': 'bar',
        'name': x,
        'marker': {'color': colors}

    }

    # Configure the chart's layout
    layout = {'title': {'text': 'impofstofverteilung',
                        'font': {
                            'color': '#ffffff'}
                        },
              'xaxis': {'title': 'Impfstoff', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'false'},
              'yaxis': {'title': 'Anzahl der Länder Pro impfstoff', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'true'},
              'plot_bgcolor': 'black', 'paper_bgcolor': 'black', 'bordercolor': '#ffffff'}

    # Pass trace and layout in the context
    context = {"trace": trace,
               "layout": layout,
               "title": "Impfstofverteilung"}

    return render(request, "chart.html", context)


def scatter_chart_view(request):
    """
    Scatter Chart
    :param request:
    :return:
    """
    # Generate a list of random numbers
    df_cp = vaccin_count()

    x = df_cp[:20].index.values.tolist()
    y = df_cp[:20]['Zahl der geimpften Menschen'].values.tolist()

    # Create a trace json to hold graph data
    trace = {
        'x': x,
        'y': y,
        'type': 'bar',

    }

    # Configure the chart's layout
    layout = {'title': {'text': 'Anzahl der geimpften Menschen pro Land',
                        'font': {
                            'color': '#ffffff'}
                        },
              'xaxis': {'title': 'X-axis', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'false'},
              'yaxis': {'title': 'Y-axis', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'true'},
              'plot_bgcolor': 'black', 'paper_bgcolor': 'black', 'bordercolor': '#ffffff'}

    # Pass trace and layout in the context
    context = {"trace": trace,
               "layout": layout,
               "title": "Land - Anzahl der Geimpften"}

    return render(request, "chart.html", context)


def pie_chart_view(request):
    """
    Pie Chart
    :param request:
    :return:
    """
    df = vaccin_eu_count()
    x = df.index.tolist()
    y = df.values.tolist()

    # Bar chart colors
    colors = ['limegreen', ] * 5

    # Create a trace json to hold graph data
    trace = {
        'x': x,
        'y': y,
        'type': 'bar',
        'name': x,
        'marker': {'color': colors}

    }

    # Configure the chart's layout
    layout = {'title': {'text': 'impofstofverteilung',
                        'font': {
                            'color': '#ffffff'}
                        },
              'xaxis': {'title': 'Impfstoff', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'false'},
              'yaxis': {'title': 'Anzahl der Länder Pro impfstoff', 'color': '#DCDCDC', 'mirror': 'true',
                        'showline': 'true'},
              'plot_bgcolor': 'black', 'paper_bgcolor': 'black', 'bordercolor': '#ffffff'}

    # Pass trace and layout in the context
    context = {"trace": trace,
               "layout": layout,
               "title": "Impfstofverteilung"}


    return render(request, "chart.html", context)


def bubble_chart_view(request):
    """
    Bubble Chart
    :param request:
    :return:
    """

    # Create a trace json to hold graph data
    trace = {
          'x': [1, 2, 3, 4],
          'y': [10, 11, 12, 13],
          'mode': 'markers',
          'marker': {
            'size': [40, 60, 80, 100]
          }


    }

    # Configure the chart's layout
    layout = {'title': {'text': 'Anzahl der geimpten Menschen pro EU-Land',
                        'font': {
                            'color': '#ffffff'}
                        },
              'xaxis': {'title': 'EU-Land', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'false'},
              'yaxis': {'title': 'Anzahl der Geimpten', 'color': '#DCDCDC', 'mirror': 'true', 'showline': 'true'},
              'plot_bgcolor': 'black', 'paper_bgcolor': 'black', 'bordercolor': '#ffffff'}

    # Pass trace and layout in the context
    context = {"trace": trace,
               "layout": layout,
               "title": "Anzahl der Geimpften Menschen pro EU-Land"}

    return render(request, "chart.html", context)


def example_view(request):
    trace = {type: "scattermapbox", 'lon': [-86], 'lat': [34], 'marker': {'size': 20, 'color': 'purple'}},\
        {   type: "choroplethmapbox", 'locations': ["AL"], 'z': [10], 'coloraxis': "coloraxis",
            'geojson': {type: "Feature", id: "AL", 'geometry': {type: "Polygon", 'coordinates': [[
                [-86, 35], [-85, 34], [-85, 32], [-85, 32], [-85, 32], [-85, 32], [-85, 31],
                [-86, 31], [-87, 31], [-87, 31], [-88, 30], [-88, 30], [-88, 30], [-88, 30],
                [-88, 34], [-88, 35]]] }}}


    layout = {'width': 600, 'height': 400, 'mapbox': {'style': 'streets',
                                                'center': {'lon': -86, 'lat': 33}, 'zoom': 5}, 'marker': {'line': {'color': "blue"}},
              'coloraxis': {'showscale': 'false', 'colorscale': "Viridis"}};

    context = {"trace": trace,
               "layout": layout,
               "title": "Anzahl der Geimpften Menschen pro EU-Land"}


    return render(request,"chart.html",context)


def home_page(request):
    context = {
        'title': "its my home page",
    }
    return render(request,"homepage.html",context)