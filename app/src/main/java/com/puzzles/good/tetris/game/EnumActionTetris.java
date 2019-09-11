package com.puzzles.good.tetris.game;

/**
 * 
 * Enumerated with the actions that the implementation of tetris notifies to the observers
 * 
 * @author Diego Garc�a Mart�n
 *
 */
public enum EnumActionTetris {
	MoveRightPiece,MoveLeftPiece,MoveDownPiece,RotatePiece,EndGame,
	NextPiece,RemoveRow,LevelChange,ScoreChange,LinesChange;
}
