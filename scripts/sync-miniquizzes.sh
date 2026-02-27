#!/bin/bash
#
# Syncs the miniquizzes/ directory from the APCS repo to the assessments repo.
# Only copies the apcs/ subdirectory in assessments — does not touch intro/.
#
# Usage: ./scripts/sync-miniquizzes.sh
#

APCS_REPO="$(cd "$(dirname "$0")/.." && pwd)"
ASSESSMENTS_REPO="$HOME/Developer/assessments"

APCS_MINIQUIZZES="$APCS_REPO/miniquizzes"
ASSESSMENTS_APCS="$ASSESSMENTS_REPO/apcs"

if [ ! -d "$APCS_MINIQUIZZES" ]; then
    echo "Error: miniquizzes directory not found at $APCS_MINIQUIZZES"
    exit 1
fi

if [ ! -d "$ASSESSMENTS_REPO" ]; then
    echo "Error: assessments repo not found at $ASSESSMENTS_REPO"
    exit 1
fi

# Sync miniquizzes to assessments/apcs/ (delete files in dest that aren't in source)
rsync -av --delete "$APCS_MINIQUIZZES/" "$ASSESSMENTS_APCS/"

echo "Synced miniquizzes -> assessments/apcs/"
