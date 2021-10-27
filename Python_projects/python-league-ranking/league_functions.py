import pandas as pd
import sys
import os


#url = 'C:/Users/khale/OneDrive/Desktop/tigerlab-python-test-ranking/sample-input.txt'

"""
    Url for our input folder
"""
url = os.path.join(os.getcwd(), "sample-input.txt")


def readUrl(url):
    """Read the Input folder with Pandas in form Dataframe and trim all column to avoid white spaces in every String.

            If the pandas kann't read the url we except an error

            Parameters
            ----------
            url : str, o

            Raises
            ------
            FileNotFoundError:
                if the Url not true.
            """
    trim = lambda x: x.strip() if type(x) is str else x
    try:
        data = pd.read_csv(url,header=None)
        df=data.copy()
    except FileNotFoundError:
        print('Url not true, try another url')
        df = df.applymap(trim)
    return df

def leagueBeginn(df):
    """
    Generate a dictionary with keys : all team  and value = 0

    param df: dataframe
    return: dictionary
            result = > {'FC Super': 0, 'Rebels': 0, 'Misfits': 0, 'Crazy Ones': 0, 'Fantastics': 0}
    """
    team = []
    for i, j in zip(df[0], df[1]):
        team.extend((i[:-1].strip(), j[:-1].strip()))
    result = dict.fromkeys(set(team), 0)
    return result

def pointSumDictionary(df,result):
    """
    give point to every team with basis game result
            egality += 0
            win += 3
            loss += 1

    :param df: dataframe
    :param result: dictionary
    :return: dic
            point sum after games => {'FC Super': 1, 'Rebels': 1, 'Misfits': 0, 'Crazy Ones': 5, 'Fantastics': 6}
    """
    for i,j in zip(df[0],df[1]):
        if int(i[-1]) == int(j[-1]):
            result[i[:-1].strip()] +=1
            result[j[:-1].strip()] +=1
        if int(i[-1]) > int(j[-1]):
            result[i[:-1].strip()] +=3
        if int(i[-1]) < int(j[-1]):
            result[j[:-1].strip()] +=3
    return result

def sortRanking(dict):
    """
        Sort a dictionary descending by dictionary keys

    :param dict:
    :return: dictionary
            sorted_dict = > {'Fantastics': 6, 'Crazy Ones': 5, 'FC Super': 1, 'Rebels': 1, 'Misfits': 0}


    """
    sorted_tuples = sorted(dict.items(), key=lambda item: item[1], reverse=True)
    # [('Fantastics ', 6), ('Crazy Ones ', 5), (' Rebels ', 1), (' FC Super ', 1), (' Misfits ', 0)]
    sorted_dict = {k: v for k, v in sorted_tuples}
    return sorted_dict



def printLeagueRanking(sorted_dict):
    """
        Print Ranking Table

    :param sorted_dict:
    :return: 1. Fantastics, 6 pts
            2. Crazy Ones, 5 pts
            3. FC Super, 1 pt
            3. Rebels, 1 pt
            5. Misfits, 0 pts
    """
    output = ""
    p = 0
    for index,(k, v) in enumerate(sorted_dict.items()):
        #print(index,k,v)
        output += f"{index if v==p else index+1}. {k}, {v} {'pt' if v==1 else 'pts'}\n"
        #sys.stdout.write(f"{index if v==p else index+1}. {k}, {v} {'pt' if v==1 else 'pts'}\n")
        p = v
    sys.stdout.write(output)
    return output

"""
    resume all Fonctions in one fonction 
"""

def leagueRanking():
    df = readUrl(url)
    beginn_dict = leagueBeginn(df)
    result_dict = pointSumDictionary(df,beginn_dict)
    sortet_dict = sortRanking(result_dict)
    printLeagueRanking(sortet_dict)