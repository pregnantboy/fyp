//
//  RuleTableViewCell.h
//  FuzzyAIBuilder
//
//  Created by Ian Chen on 25/1/16.
//  Copyright © 2016 Ian Chen. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface RuleTableViewCell : NSTableCellView
@property (weak) IBOutlet NSImageView *background;
@property (weak) IBOutlet NSTextField *ruleText;

- (NSString *)getRuleString;
- (void)setRuleString:(NSString *)ruleString;
@end
