import pandas as pd
import numpy as np
from countrygroups import EUROPEAN_UNION


url = 'static/country_vaccinations.csv'

def read_data_frame():
    df = pd.read_csv(url)
    df.copy()
    return df
"""
def vaccin_list():
    df = read_data_frame()
    pre_vaccin_df = df['vaccines'].str.split(',', expand=True)
    # np.unique(np.pre_vaccin_df.values.ravel())
    vaccin_List = filter(None, pre_vaccin_df.values.ravel())
    vaccin_List = np.unique(np.char.strip([x for x in pre_vaccin_df.values.ravel() if x is not None]).reshape(-1, ))
    return vaccin_list
"""

def vaccin_country_df():
    df = read_data_frame()
    pre_vaccin_df = df['vaccines'].str.split(',', expand=True)
    # np.unique(np.pre_vaccin_df.values.ravel())
    vaccin_List = filter(None, pre_vaccin_df.values.ravel())
    vaccin_List = np.unique(np.char.strip([x for x in pre_vaccin_df.values.ravel() if x is not None]).reshape(-1, ))
    country_vaccin = [df[['country']][df['vaccines'].str.contains(i)]['country'].unique() for i in vaccin_List]
    # print(len(country_vaccin),len(vaccin_List))
    lst = [vaccin_List, country_vaccin]
    dfv = pd.DataFrame(lst).transpose().rename(columns={0: 'Vaccin', 1: 'countries'})
    # dfv['total_coutries'] =
    dfv['total_country'] = dfv['countries'].str.len()
    dfv.sort_values(by=['total_country'], inplace=True, ascending=False)
    return dfv


def vaccin_count():
    df = read_data_frame()
    countries_v = df['country'].unique()
    person_vaccinated = []
    for country in countries_v:
        person_vaccinated.append(df[df['country'] == country]['total_vaccinations'].max())
    df_cp = pd.DataFrame(person_vaccinated, countries_v, columns={'Zahl der geimpften Menschen'})
    df_cp.sort_values(by=['Zahl der geimpften Menschen'], inplace=True, ascending=False)
    #df_cp[:20]['Zahl der geimpften Menschen']
    return df_cp

    # data_2 = data.groupby('country')['total_vaccinations'].max() # Zweite lösung
euCountries = EUROPEAN_UNION.names

def vaccin_eu_count():
    df = read_data_frame()
    vacc_eu_Countries = df[df['country'].isin(euCountries)]
    data = vacc_eu_Countries.groupby('country')['total_vaccinations'].max().sort_values(ascending=False)
    return data