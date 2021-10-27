import unittest
from league_functions import *
import pandas as pd

"""
Test league_fonctions.py fonctions
"""

class TestLeagueFileMethods(unittest.TestCase):


    def test_leagueBegin(self):
        """
        test  leagueBegin fonction
        :return:
        """
        df =  pd.DataFrame({0: ['a 1', 'c 1'], 1: ['b 2', 'a 1']})
        self.assertEqual(leagueBeginn(df), {'b': 0, 'a': 0, 'c': 0})

    def test_pointSumDictionary(self):
        """
        test pointSumDictionary fonction
        test if points added correctly to  a dictionary value
                egaly +=0
                win +=3
                loss +=1


        """
        dic = {'b': 0, 'a': 0, 'c': 0}
        df = pd.DataFrame({0: ['a 1', 'c 1'], 1: ['b 2', 'a 1']})
        self.assertEqual(pointSumDictionary(df, dic), {'b': 3, 'a': 1, 'c': 1})

    def test_sortRanking(self):
        """
            test sortRanking fonction
            test if a dictionary sortet descending  correctly by value
        """
        dic = {'b': 2, 'a': 4, 'c': 6}
        self.assertEqual(sortRanking(dic),{'c': 6, 'a': 4, 'b': 2})







if __name__ == '__main__':
    unittest.main()