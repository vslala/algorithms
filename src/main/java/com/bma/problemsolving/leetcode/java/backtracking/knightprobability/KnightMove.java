package com.bma.problemsolving.leetcode.java.backtracking.knightprobability;

import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class KnightMove {
    private final Integer x;
    private final Integer y;

    private KnightMove calcNewMove(KnightMove move) {
        final int row = this.x + move.getX();
        final int col = this.y + move.getY();
        return new KnightMove(row, col);
    }

    private boolean isInBound(Integer boardSize) {
        return this.getX() > -1 && this.getX() < boardSize
                && this.getY() > -1 && this.getY() < boardSize;
    }

    public Set<KnightMove> findAllBoundedMoves(Set<KnightMove> possibleMoves, int boardSize) {
        return possibleMoves.stream()
                .map(this::calcNewMove)
                .filter(newMove -> newMove.isInBound(boardSize))
                .collect(Collectors.toSet());
    }

    public float totalBoundedMove(Set<KnightMove> possibleMoves, Integer boardSize) {
        return possibleMoves.stream()
                .map(this::calcNewMove)
                .filter(newMove -> newMove.isInBound(boardSize))
                .count();
    }
}
