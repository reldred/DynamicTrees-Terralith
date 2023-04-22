package com.djs.dtterralith.cellkits;

import com.djs.dtterralith.DynamicTreesTerralith;
import com.djs.dtterralith.cellkits.cell.PoplarBranchCell;
import com.djs.dtterralith.cellkits.cell.PoplarLeafCell;
import com.djs.dtterralith.cellkits.cell.PoplarTopBranchCell;
import com.djs.dtterralith.cellkits.cell.SparseBranchCell;
import com.ferreusveritas.dynamictrees.api.cell.Cell;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.cell.CellNull;
import com.ferreusveritas.dynamictrees.api.cell.CellSolver;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.cell.CellKits;
import com.ferreusveritas.dynamictrees.cell.MetadataCell;
import com.ferreusveritas.dynamictrees.cell.NormalCell;
import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import net.minecraft.resources.ResourceLocation;

public class DTTerralithCellKits {

    public static void register(final Registry<CellKit> registry) {
        registry.registerAll(SPARSE, POPLAR);
    }

    public static final CellKit SPARSE = new CellKit(new ResourceLocation(DynamicTreesTerralith.MOD_ID, "sparse")) {

        private final Cell sparseBranch = new SparseBranchCell();
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTTerralithLeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }

    };

    public static final CellKit POPLAR = new CellKit(new ResourceLocation(DynamicTreesTerralith.MOD_ID, "poplar")) {

        private final Cell poplarBranch = new PoplarBranchCell();
        private final Cell poplarTopBranch = new PoplarTopBranchCell();
        private final Cell poplarUpperTrunk = new NormalCell(4);

        private final Cell[] poplarLeaves = new Cell[] {
                CellNull.NULL_CELL,
                new PoplarLeafCell(1),
                new PoplarLeafCell(2),
                new PoplarLeafCell(3),
                new PoplarLeafCell(4),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {
                0x0412, 0x0311, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return poplarLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return poplarTopBranch;
            if (radius == 1) return poplarBranch;
            if (radius < 4) return poplarUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTTerralithLeafClusters.POPLAR;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

}
